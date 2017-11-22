package com.org.servlets;

import com.org.jdbc.Employeejdbc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Elat on 07.04.2017.
 */


@WebServlet(urlPatterns = EmployeesGet.PATH)
public class EmployeesGet extends HttpServlet {

    public static final String PAGE = "/allEmployeesList.jsp";
    public static final String PATH = "/allEmployeesList";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("employees", Employeejdbc.getInstance().read());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(PAGE);
        dispatcher.forward(req, resp);
    }


}
