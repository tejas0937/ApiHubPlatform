package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.UserDAO;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {

            resp.sendRedirect(
                req.getContextPath() + "/login-page"
            );

            return;
        }

        try (Connection con = DBUtil.getConnection()) {

            UserDAO userDAO = new UserDAO(con);

            User user =
                    userDAO.findByEmailAndPassword(
                            email,
                            password
                    );

            if (user == null) {

                resp.sendRedirect(
                    req.getContextPath() + "/login-page"
                );

                return;
            }

            List<String> roles =
                    userDAO.findRolesByUserId(
                            user.getId()
                    );

            HttpSession session =
                    req.getSession(true);

            session.setAttribute("user", user);
            session.setAttribute("roles", roles);

            // ✅ role based routing

            if (roles.contains("SUPER_ADMIN")) {

                resp.sendRedirect(
                    req.getContextPath()
                    + "/superadmin/dashboard"
                );

            } else if (roles.contains("PLATFORM_ADMIN")) {

                resp.sendRedirect(
                    req.getContextPath()
                    + "/platform/dashboard"
                );

            } else if (roles.contains("ORG_ADMIN")) {

                resp.sendRedirect(
                    req.getContextPath()
                    + "/org/dashboard"
                );

            } else if (roles.contains("ORG_EMP")) {

                resp.sendRedirect(
                    req.getContextPath()
                    + "/org/employee-dashboard"
                );

            } else {

                resp.sendRedirect(
                    req.getContextPath()
                    + "/login-page"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            resp.setStatus(500);

            resp.getWriter().write(
                "Login failed due to server error."
            );
        }
    }
}