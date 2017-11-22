package com.org.servlets;

import com.org.MyException;
import com.org.entities.Department;
import com.org.jdbc.Departmentjdbc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Elat on 11.04.2017.
 */
@WebServlet(urlPatterns = EmployeesByDepartmentsGet.PATH)
public class EmployeesByDepartmentsGet extends HttpServlet {

    public static final String PAGE = "/employeesList.jsp";
    public static final String PATH = "/employeeById";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        Department department = Departmentjdbc.getInstance().getById(id);
        try {
            request.setAttribute("employees", Departmentjdbc.getInstance().getEmployeesById(department));
            if (Departmentjdbc.getInstance().getEmployeesById(department).isEmpty()) {
                String sd = "There is not any employee yet.";
                request.setAttribute("noEmp", sd);
            }
            System.out.println(Departmentjdbc.getInstance().getEmployeesById(department));
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            String message = "You are trying to get a list of employees of the department, which is not in the database.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (MyException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }

}
