package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.PlanDAO;
import com.apihub.model.Plan;
import com.apihub.util.DBUtil;

@WebServlet("/org/plans")
public class OrgPlanListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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

        try (Connection con = DBUtil.getConnection()) {

            PlanDAO dao = new PlanDAO(con);

            List<Plan> plans = dao.findAll();

            req.setAttribute("plans", plans);

            req.getRequestDispatcher(
                "/WEB-INF/views/org/plans.jsp"
            ).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Unable to load plans");
        }
    }
}
