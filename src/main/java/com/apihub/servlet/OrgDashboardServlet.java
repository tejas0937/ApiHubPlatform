package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.ApiAnalyticsDAO;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

@WebServlet("/org/dashboard")
public class OrgDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            HttpSession session = req.getSession(false);

            if (session == null) {
                resp.sendRedirect(
                    req.getContextPath() + "/login"
                );
                return;
            }

            User user =
                (User) session.getAttribute("user");

            try (Connection con = DBUtil.getConnection()) {

                ApiAnalyticsDAO dao =
                        new ApiAnalyticsDAO(con);

                long orgId = user.getOrganizationId();

                req.setAttribute(
                    "totalApiCalls",
                    dao.countTotalApiCalls(orgId)
                );
                
                

                req.setAttribute(
                    "totalApiKeys",
                    dao.countApiKeys(orgId)
                );

                req.setAttribute(
                    "usageLogs",
                    dao.getUsageLogs(orgId)
                );

                req.getRequestDispatcher(
                    "/WEB-INF/views/org/dashboard.jsp"
                ).forward(req, resp);
            }

            System.out.println(
            	    "ORG ID = " + user.getOrganizationId()
            	);
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}