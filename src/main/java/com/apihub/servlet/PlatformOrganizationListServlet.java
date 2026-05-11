package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.OrganizationDAO;
import com.apihub.model.Organization;
import com.apihub.util.DBUtil;

@WebServlet("/platform/organizations")
public class PlatformOrganizationListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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

        try (Connection con = DBUtil.getConnection()) {

            OrganizationDAO dao = new OrganizationDAO(con);

            List<Organization> list = dao.findAll();

            req.setAttribute("organizations", list);

            req.getRequestDispatcher(
                    "/WEB-INF/views/platform/organizations.jsp"
            ).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Unable to load organizations");
        }
    }
}
