package com.org.servlets;

import com.org.MyException;
import com.org.entities.Department;
import com.org.jdbc.Departmentjdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Elat on 11.04.2017.
 */
@WebServlet(urlPatterns = DepartmentDelete.PATH)
public class DepartmentDelete extends HttpServlet {

    public static final String PATH = "/departmentDelete";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String ID = request.getParameter("id");
            if ((ID.matches("[a-zA-Z]*"))) {
                String message = "Id of department can not be " + ID + "!";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException(message);
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = Departmentjdbc.getInstance().getById(id);
        try {
            if (!Departmentjdbc.getInstance().getEmployeesById(department).isEmpty()) {
                String message = "You are trying to delete department with employee(s). Firstly you must change theirs department.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                throw new MyException("Exception while deleting.");
            } else{
            Departmentjdbc.getInstance().delete(department);}

        }
        catch (NullPointerException ne) {
            ne.printStackTrace();
            String message = "You are trying to delete department which is not in the database!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (MyException e) {
            e.printStackTrace();
        }
        response.sendRedirect(DepartmentGet.PATH);
    }
}
