package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.SubscriptionDAO;
import com.apihub.model.Subscription;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

@WebServlet("/org/subscribe")
public class OrgSubscribeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath()+"/login-page");
            return;
        }

        List<String> roles =
                (List<String>) session.getAttribute("roles");

        if (roles == null || !roles.contains("ORG_ADMIN")) {
            resp.setStatus(403);
            return;
        }

        String planIdStr = req.getParameter("planId");

        User user = (User) session.getAttribute("user");

        try (Connection con = DBUtil.getConnection()) {

            Subscription s = new Subscription();
            s.setOrganizationId(user.getOrganizationId());
            s.setPlanId(Long.parseLong(planIdStr));
            s.setStartDate(LocalDate.now());
            s.setStatus("ACTIVE");

            SubscriptionDAO dao = new SubscriptionDAO(con);
            dao.create(s);

            resp.sendRedirect(req.getContextPath()+"/org/dashboard-page");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Subscription failed");
        }
    }
}
