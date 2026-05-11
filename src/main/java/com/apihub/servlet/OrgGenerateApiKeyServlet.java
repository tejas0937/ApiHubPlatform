package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.ApiKeyDAO;
import com.apihub.model.ApiKey;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

@WebServlet("/org/generate-key")
public class OrgGenerateApiKeyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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

        String subscriptionIdStr = req.getParameter("subscriptionId");

        if (subscriptionIdStr == null || subscriptionIdStr.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/org/api-keys");
            return;
        }

        try (Connection con = DBUtil.getConnection()) {

            ApiKey key = new ApiKey();
            key.setOrganizationId(user.getOrganizationId());
            key.setSubscriptionId(Long.parseLong(subscriptionIdStr));
            key.setApiKey(UUID.randomUUID().toString());
            key.setStatus("ACTIVE");

            ApiKeyDAO dao = new ApiKeyDAO(con);
            dao.insert(key);

            resp.sendRedirect(
                    req.getContextPath() + "/org/api-keys"
            );

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );
        }
    }@Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws IOException {

                resp.sendRedirect(req.getContextPath() + "/org/api-keys");
            }

}
