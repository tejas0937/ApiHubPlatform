package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.ApiServiceDAO;
import com.apihub.model.ApiService;
import com.apihub.util.DBUtil;

@WebServlet("/platform/add-api")
public class PlatformAddApiServiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login-page");
            return;
        }

        List<String> roles =
                (List<String>) session.getAttribute("roles");

        if (roles == null || !roles.contains("PLATFORM_ADMIN") && !roles.contains("SUPER_ADMIN")) {
            resp.setStatus(403);
            return;
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String status = req.getParameter("status");

        try (Connection con = DBUtil.getConnection()) {

            ApiService api = new ApiService();
            api.setName(name);
            api.setDescription(description);
            api.setStatus(status);

            ApiServiceDAO dao = new ApiServiceDAO(con);
            dao.insert(api);

            resp.sendRedirect(
                req.getContextPath() + "/platform/apis"
            );

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Failed to add api service");
        }
    }
}
