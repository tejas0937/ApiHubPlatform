package com.apihub.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apihub.service.OrganizationRegistrationService;

@WebServlet("/register")
public class RegisterOrganizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orgName = req.getParameter("orgName");
        String orgEmail = req.getParameter("orgEmail");
        String adminName = req.getParameter("adminName");
        String adminEmail = req.getParameter("adminEmail");
        String password = req.getParameter("password");

        try {

            OrganizationRegistrationService service =
                    new OrganizationRegistrationService();

            service.registerOrganizationWithAdmin(
                    orgName, orgEmail,
                    adminName, adminEmail,
                    password
            );

            // ✅ after successful registration → login page
            resp.sendRedirect(req.getContextPath() + "/login-page");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("Registration failed");
        }
    }
}
