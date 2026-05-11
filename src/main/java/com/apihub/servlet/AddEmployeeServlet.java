package com.apihub.servlet;

import com.apihub.model.User;
import com.apihub.service.OrgEmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/org/add-employee")
public class AddEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private OrgEmployeeService service =
            new OrgEmployeeService();
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendError(401);
            return;
        }

        User loggedUser = (User) session.getAttribute("user");

        try {
            OrgEmployeeService service = new OrgEmployeeService();

            List<User> employees =
                    service.getEmployeesByOrganization(
                            loggedUser.getOrganizationId()
                    );

            req.setAttribute("employees", employees);

            req.getRequestDispatcher(
                    "/WEB-INF/views/org/add-employee.jsp"
            ).forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain");

        try {

            HttpSession session = req.getSession(false);

            if (session == null) {
                resp.setStatus(401);
                resp.getWriter().write("Not logged in");
                return;
            }

            User loggedUser =
                    (User) session.getAttribute("user");

            List<String> roles =
                    (List<String>) session.getAttribute("roles");

            if (loggedUser == null ||
                roles == null ||
                !roles.contains("ORG_ADMIN")) {

                resp.setStatus(403);
                resp.getWriter().write("Access denied");
                return;
            }

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String role = req.getParameter("role");

            if (!"VIEWER".equals(role) &&
                !"FINANCE".equals(role)) {

                resp.setStatus(400);
                resp.getWriter().write("Invalid role");
                return;
            }

            service.addEmployee(
                    loggedUser.getOrganizationId(),
                    name,
                    email,
                    password,
                    role
            );

            resp.sendRedirect(
            	    req.getContextPath() + "/org/add-employee"
            	);


        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("Error creating employee");
        }
    }
}
