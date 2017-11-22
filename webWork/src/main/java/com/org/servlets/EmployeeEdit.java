package com.org.servlets;

import com.org.MyException;
import com.org.Validation;
import com.org.entities.Employee;
import com.org.jdbc.Employeejdbc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Elat on 10.04.2017.
 */
@WebServlet(urlPatterns = EmployeeEdit.PATH)
public class EmployeeEdit extends HttpServlet {
    public static final String PAGE = "/employeeEdit.jsp";
    public static final String PATH = "/employeeEdit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id =1;
        try {
            if((request.getParameter("id").matches("[a-zA-Z]*"))){
                String message = "Id of employee can not be "+request.getParameter("id")+"!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException(message);
            }
            id = Integer.parseInt(request.getParameter("id"));

        } catch (Exception e) {
            response.sendRedirect(EmployeesGet.PATH);
            e.printStackTrace();
        }

        Employee employee = Employeejdbc.getInstance().getById(id);



        request.setAttribute("employee", employee);
        try {
            request.getAttribute("employee").toString().isEmpty();
        } catch (NullPointerException e) {
            e.printStackTrace();
            String message = "You are trying to edit an employee which is not in the database!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        request.setAttribute("pageTitle", "Edit Employee");

        HttpSession session = request.getSession();
        int var = (int) session.getAttribute("id");
        request.setAttribute("idList", var);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Employee employee = Employeejdbc.getInstance().getById(id);
            Validation validation = new Validation();
            String name = request.getParameter("name").trim();
            String surname = request.getParameter("surname").trim();
            String email = request.getParameter("email").trim();
            String date = request.getParameter("date").trim();
            String depid = request.getParameter("depId").trim();

            if (validation.validateEmployeeEdit(request, name, surname, email, date, depid)) {
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException("Exception while updating data!");
            } else {
                employee.setName(request.getParameter("name").trim());
                employee.setSurname(request.getParameter("surname").trim());
                employee.setEmail(request.getParameter("email").trim());
                employee.setDate(Date.valueOf(request.getParameter("date").trim()));
                employee.setDepId(Integer.parseInt(request.getParameter("depId").trim()));
                Employeejdbc.getInstance().update(employee);}
                int idd = employee.getDepId();
                response.sendRedirect("/employeeById?id=" + idd);

        } catch (SQLException se) {
            se.printStackTrace();
            String message = "This email of employee is in base or there is not department with such ID!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}