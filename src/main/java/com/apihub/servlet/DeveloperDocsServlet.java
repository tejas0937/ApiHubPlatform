package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apihub.dao.ApiServiceDAO;
import com.apihub.model.ApiService;
import com.apihub.util.DBUtil;

@WebServlet("/docs")
public class DeveloperDocsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        try (Connection con = DBUtil.getConnection()) {

            ApiServiceDAO dao = new ApiServiceDAO(con);

            List<ApiService> apis = dao.findAll();

            req.setAttribute("apis", apis);

            req.getRequestDispatcher(
                "/WEB-INF/views/common/developer-docs.jsp"
            ).forward(req, resp);

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}