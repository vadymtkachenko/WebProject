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
@WebServlet(urlPatterns = DepartmentAdd.PATH)
public class DepartmentAdd extends HttpServlet {
    public static final String PAGE = "/departmentAdd.jsp";
    public static final String PATH = "/departmentAdd";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("department", new Department());
        request.setAttribute("pageTitle", "Add Department");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PAGE);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, RuntimeException {
        try {
            Department department = new Department();
            Validation validation = new Validation();
            String name = request.getParameter("name").trim();
            String number = request.getParameter("number").trim();
            if (validation.validateDepartment(request, name, number)) {
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException("Exception while adding data!");
            } else {
                department.setName(request.getParameter("name").trim());
                department.setNumber(Integer.parseInt(request.getParameter("number").trim()));
                Departmentjdbc.getInstance().create(department);
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