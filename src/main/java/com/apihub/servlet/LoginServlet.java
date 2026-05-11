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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	 
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {
            resp.sendRedirect(req.getContextPath() + "/login-page");
            return;
        }

        try (Connection con = DBUtil.getConnection()) {

            UserDAO userDAO = new UserDAO(con);

            User user = userDAO.findByEmailAndPassword(email, password);

            if (user == null) {
                resp.sendRedirect(req.getContextPath() + "/login-page");
                return;
            }

            List<String> roles = userDAO.findRolesByUserId(user.getId());

            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("roles", roles);

            // ✅ role based dashboard routing

            if (roles.contains("SUPER_ADMIN")) {

                req.getRequestDispatcher(
                        "/WEB-INF/views/superadmin/dashboard.jsp"
                ).forward(req, resp);

            } else if (roles.contains("PLATFORM_ADMIN")) {

                req.getRequestDispatcher(
                        "/WEB-INF/views/platform/dashboard.jsp"
                ).forward(req, resp);

            } else if (roles.contains("ORG_ADMIN")) {

                req.getRequestDispatcher(
                        "/WEB-INF/views/org/dashboard.jsp"
                ).forward(req, resp);

            } else if (roles.contains("ORG_EMP")) {

                req.getRequestDispatcher(
                        "/WEB-INF/views/org/employee-dashboard.jsp"
                ).forward(req, resp);

            } else {

                resp.sendRedirect(req.getContextPath() + "/login-page");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("Login failed due to server error.");
        }
    }
}
