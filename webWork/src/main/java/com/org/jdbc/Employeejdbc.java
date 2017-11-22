package com.org.jdbc;

import com.org.MyException;
import com.org.entities.ConnectionManager;
import com.org.entities.Employee;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Elat on 08.04.2017.
 */
public class Employeejdbc {

    private static Employeejdbc INSTANCE;

    private static Connection getEmpConnection() throws SQLException {
        ConnectionManager connectionManager = null;
        String URL = "jdbc:mysql://127.0.0.1:3306/mydb";
        String LOGIN = "root";
        String PASSWORD = "root";

        try {
            connectionManager = new ConnectionManager(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Connection connection = connectionManager.getConnection();
        return connection;
    }

    public static Employeejdbc getInstance() {
        if (INSTANCE == null) {
            synchronized (Employeejdbc.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Employeejdbc();
                }
            }
        }
        return INSTANCE;
    }


    public List<Employee> read() {


        List<Employee> employeeList = new LinkedList<>();
        try (Connection connection = getEmpConnection();
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery("SELECT * FROM mydb.employees");) {


            Employee employee = null;

            while (resultSet.next()) {

                employee = new Employee();

                employee.setId(Integer.parseInt(resultSet.getString("idEmployees")));

                employee.setName(resultSet.getString("name"));

                employee.setSurname(resultSet.getString("surname"));

                employee.setEmail(resultSet.getString("email"));

                employee.setDepId(Integer.parseInt(resultSet.getString("idDep")));

                employee.setDate(Date.valueOf(resultSet.getString("birthday")));

                employeeList.add(employee);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(employeeList);
        return employeeList;

    }

    public Employee getById(int id) {
        for (Employee emp : Employeejdbc.getInstance().read()) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    public void update(Employee employee) throws MyException {

        try (Connection connection = getEmpConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `mydb`.`employees` SET `Name`=?, `Surname`=?, `Email`=?, `idDep`=?, `Birthday`=? WHERE `idEmployees`=?;");
        ) {

            preparedStatement.setString(1, employee.getName());


            preparedStatement.setString(2, employee.getSurname());

            preparedStatement.setString(3, employee.getEmail());

            preparedStatement.setDate(5, employee.getDate());

            preparedStatement.setInt(4, employee.getDepId());

            preparedStatement.setInt(6, employee.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            throw new MyException("Exception while updating database");

        }

    }

    public void delete(Employee employee) {

        try (Connection connection = getEmpConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `mydb`.`employees` WHERE `idEmployees`=?;");
        ) {

            preparedStatement.setInt(1, employee.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }


    public void create(Employee employee) throws MyException {

        try (Connection connection = getEmpConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO `mydb`.`employees` ( `Name`, `Surname`, `Email`, `idDep`, `Birthday`) VALUES (?,?,?,?,?)");
        ) {


            ps.setString(1, employee.getName());


            ps.setString(2, employee.getSurname());

            ps.setString(3, employee.getEmail());

            ps.setDate(5, employee.getDate());

            ps.setInt(4, employee.getDepId());

            ps.executeUpdate();


        } catch (SQLException e) {

            throw new MyException("Exception while accessing database");
        }


    }
}
