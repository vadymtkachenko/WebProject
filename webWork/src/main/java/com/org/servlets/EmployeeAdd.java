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

/**
 * Created by Elat on 09.04.2017.
 */

@WebServlet(urlPatterns = EmployeeAdd.PATH)
public class EmployeeAdd extends HttpServlet {
    public static final String PAGE = "/employeeAdd.jsp";
    public static final String PATH = "/employeeAdd";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("employee", new Employee());
        request.setAttribute("pageTitle", "Add Employee");
        HttpSession session = request.getSession();
        int var = (int) session.getAttribute("id");
        request.setAttribute("idList", var);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Employee employee = new Employee();
            Validation validation = new Validation();
            String name = request.getParameter("name").trim();
            String surname = request.getParameter("surname").trim();
            String email = request.getParameter("email").trim();
            String date = request.getParameter("date").trim();
            if (validation.validateEmployee(request, name, surname, email, date)) {
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException("Exception while add data!");
            } else {
                employee.setName(request.getParameter("name").trim());
                employee.setSurname(request.getParameter("surname").trim());
                employee.setEmail(request.getParameter("email").trim());
                employee.setDate(Date.valueOf(request.getParameter("date").trim()));

                HttpSession session = request.getSession();
                int var = (int) session.getAttribute("id");
                employee.setDepId(var);
                Employeejdbc.getInstance().create(employee);
                response.sendRedirect("/employeeById?id=" + var);
            }
        } catch (MyException se) {
            se.printStackTrace();
            String message = "This email of employee is in base. Please fill another email.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }


    }
}
