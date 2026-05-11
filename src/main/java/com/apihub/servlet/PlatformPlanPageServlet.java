package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.ApiServiceDAO;
import com.apihub.dao.PlanDAO;
import com.apihub.model.ApiService;
import com.apihub.model.Plan;
import com.apihub.util.DBUtil;

@WebServlet("/platform/plans")
public class PlatformPlanPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
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

        try (Connection con = DBUtil.getConnection()) {

            PlanDAO planDAO = new PlanDAO(con);
            ApiServiceDAO apiDAO = new ApiServiceDAO(con);

            List<Plan> plans = planDAO.findAll();
            List<ApiService> apis = apiDAO.findAll();

            req.setAttribute("plans", plans);
            req.setAttribute("apis", apis);

            req.getRequestDispatcher(
                    "/WEB-INF/views/platform/plans.jsp"
            ).forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Unable to load plans");
        }
    }
}
