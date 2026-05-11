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

import com.apihub.dao.PlanDAO;
import com.apihub.model.Plan;
import com.apihub.util.DBUtil;

@WebServlet("/platform/add-plan")
public class PlatformAddPlanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login-page");
            return;
        }

        List<String> roles = (List<String>) session.getAttribute("roles");

        if (roles == null || !roles.contains("PLATFORM_ADMIN") && !roles.contains("SUPER_ADMIN")) {
            resp.setStatus(403);
            return;
        }

        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String cycle = req.getParameter("cycle");
        String[] apis = req.getParameterValues("apis");

        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false);

            Plan plan = new Plan();
            plan.setName(name);
            plan.setPrice(Double.parseDouble(priceStr));
            plan.setBillingCycle(cycle);
            plan.setStatus("ACTIVE");

            PlanDAO dao = new PlanDAO(con);

            long planId = dao.insert(plan);

            if (apis != null) {
                for (String apiId : apis) {
                    dao.attachApi(planId, Long.parseLong(apiId));
                }
            }

            con.commit();

            resp.sendRedirect(req.getContextPath() + "/platform/plans");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Plan creation failed");
        }
    }
}
