package com.org.jdbc;

import com.org.MyException;
import com.org.entities.ConnectionManager;
import com.org.entities.Department;
import com.org.entities.Employee;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Elat on 11.04.2017.
 */
public class Departmentjdbc {

    private static Departmentjdbc INSTANCE;

    private static Connection getEmpConnection() {
        ConnectionManager connectionManager = null;
        try {
            connectionManager = new ConnectionManager("jdbc:mysql://127.0.0.1:3306/mydb", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connection connection = connectionManager.getConnection();
        return connection;
    }

    public static Departmentjdbc getInstance() {
        if (INSTANCE == null) {
            synchronized (Departmentjdbc.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Departmentjdbc();
                }
            }
        }
        return INSTANCE;
    }


    public List<Department> read() {


        List<Department> depList = new LinkedList<>();
        try (Connection connection = getEmpConnection();
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM mydb.departments");) {


            Department department = null;

            while (resultSet.next()) {

                department = new Department();

                department.setId(Integer.parseInt(resultSet.getString("idDepartments")));

                department.setName(resultSet.getString("name"));

                department.setNumber(Integer.parseInt(resultSet.getString("number")));


                depList.add(department);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depList;

    }

    public Department getById(int id) {
        for (Department dep : Departmentjdbc.getInstance().read()) {
            if (dep.getId() == id) {
                return dep;
            }
        }
        return null;
    }


    public void update(Department department) throws MyException {

        try (Connection connection = getEmpConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `mydb`.`departments` SET `Name`=?, `Number`=? WHERE `idDepartments`=?;")) {

            preparedStatement.setString(1, department.getName());

            preparedStatement.setInt(2, department.getNumber());

            preparedStatement.setInt(3, department.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            throw new MyException("Exception while update row 'Name' in database");

        }

    }

    public void delete(Department department){

        try (Connection connection = getEmpConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `mydb`.`departments` WHERE `idDepartments`=?;");
        ) {

            preparedStatement.setInt(1, department.getId());


            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }


    }

    public List<Employee> getEmployeesById(Department department) throws MyException {

        List<Employee> employeeList = new LinkedList<>();
        try {
            Connection connection = getEmpConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.employees where idDep = ?;");
            preparedStatement.setInt(1, department.getId());
            ResultSet resultSet = preparedStatement.executeQuery();


            Employee employee1 = null;

            while (resultSet.next()) {

                employee1 = new Employee();

                employee1.setId(Integer.parseInt(resultSet.getString("idEmployees")));

                employee1.setName(resultSet.getString("name"));

                employee1.setSurname(resultSet.getString("surname"));

                employee1.setEmail(resultSet.getString("email"));

                employee1.setDepId(Integer.parseInt(resultSet.getString("idDep")));

                employee1.setDate(Date.valueOf(resultSet.getString("birthday")));

                employeeList.add(employee1);

            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Exception while getting LIST");
        }

        //System.out.println(employeeList);
        return employeeList;

    }


    public void create(Department department) throws MyException {

        try (Connection connection = getEmpConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO `mydb`.`departments` ( `Name`, `Number`) VALUES (?,?)")) {

            ps.setString(1, department.getName());

            ps.setInt(2, department.getNumber());

            ps.executeUpdate();


        } catch (SQLException e) {

            throw new MyException("Exception while accessing database");
        }


    }


}
