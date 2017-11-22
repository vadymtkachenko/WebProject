package com.org.servlets;

import com.org.MyException;
import com.org.Validation;
import com.org.entities.Department;
import com.org.jdbc.Departmentjdbc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Elat on 11.04.2017.
 */
@WebServlet(urlPatterns = DepartmentEdit.PATH)
public class DepartmentEdit extends HttpServlet {

    public static final String PAGE = "/departmentEdit.jsp";
    public static final String PATH = "/departmentEdit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = -1;
        try {
            if ((request.getParameter("id").matches("[a-zA-Z]*"))) {
                String message = "Id of department can not be " + request.getParameter("id") + "!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException(message);
            }
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            response.sendRedirect(DepartmentGet.PATH);
            e.printStackTrace();
        }

        Department department = Departmentjdbc.getInstance().getById(id);
        request.setAttribute("department", department);
        try {
            request.getAttribute("department").toString().isEmpty();
        } catch (NullPointerException e) {
            e.printStackTrace();
            String message = "You are trying to edit the department which is not in the database!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }


        request.setAttribute("pageTitle", "Edit Dep");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Department department = Departmentjdbc.getInstance().getById(id);
            Validation validation = new Validation();
            String name = request.getParameter("name").trim();
            String number = request.getParameter("number").trim();
            if (validation.validateDepartment(request, name, number)) {
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException("Exception while add data!");
            } else {
                department.setName(request.getParameter("name").trim());
                department.setNumber(Integer.parseInt(request.getParameter("number").trim()));
                Departmentjdbc.getInstance().update(department);
                response.sendRedirect(DepartmentGet.PATH);
            }
        } catch (MyException se) {
            se.printStackTrace();
            String message = "This name of department is in base. Please rename Department.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
