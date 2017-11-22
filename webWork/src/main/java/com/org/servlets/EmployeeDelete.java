package com.org.servlets;

import com.org.MyException;
import com.org.entities.Employee;
import com.org.jdbc.Employeejdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Elat on 10.04.2017.
 */
@WebServlet(urlPatterns = EmployeeDelete.PATH)
public class EmployeeDelete extends HttpServlet {

    public static final String PAGE = "/employeesList.jsp";
    public static final String PATH = "/employeeDelete";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ID = request.getParameter("id");
            if ((ID.matches("[a-zA-Z]*"))) {
                String message = "Id of employee can not be " + ID + "!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException(message);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = Employeejdbc.getInstance().getById(id);
        try {
            Employeejdbc.getInstance().delete(employee);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            String message = "You are trying to delete an employee which is not in the database!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        int idd = employee.getDepId();
        response.sendRedirect("/employeeById?id=" + idd);
    }
}

