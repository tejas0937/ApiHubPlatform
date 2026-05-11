package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apihub.dao.ApiKeyDAO;
import com.apihub.model.ApiKey;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

@WebServlet("/org/api-keys")
public class OrgApiKeyPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        @SuppressWarnings("unchecked")
        List<String> roles =
                (List<String>) session.getAttribute("roles");

        if (roles == null || !roles.contains("ORG_ADMIN")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try (Connection con = DBUtil.getConnection()) {

            ApiKeyDAO dao = new ApiKeyDAO(con);

            List<ApiKey> keys =
                    dao.findByOrganization(user.getOrganizationId());

            req.setAttribute("keys", keys);

            req.getRequestDispatcher(
                    "/WEB-INF/views/org/api-keys.jsp"
            ).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );
        }
    }
}
