package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.UserDAO;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

@WebServlet("/superadmin/platform-admins")
public class SuperPlatformAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if(session==null){
            resp.sendRedirect(req.getContextPath()+"/login-page");
            return;
        }

        List<String> roles =
            (List<String>) session.getAttribute("roles");

        if(roles==null || !roles.contains("SUPER_ADMIN")){
            resp.setStatus(403); return;
        }

        try(Connection con = DBUtil.getConnection()){

            UserDAO dao = new UserDAO(con);

            req.setAttribute(
                "admins",
                dao.findAllPlatformAdmins()
            );

            req.getRequestDispatcher(
                "/WEB-INF/views/superadmin/platform-admins.jsp"
            ).forward(req, resp);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        List<String> roles =
            (List<String>) session.getAttribute("roles");

        if(roles==null || !roles.contains("SUPER_ADMIN")){
            resp.setStatus(403); return;
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try(Connection con = DBUtil.getConnection()){

            User u = new User();
            u.setFullName(name);
            u.setEmail(email);
            u.setPasswordHash(password); // same logic as your login
            u.setStatus("ACTIVE");

            UserDAO dao = new UserDAO(con);

            long userId = dao.insertPlatformAdmin(u);

            long roleId = dao.getRoleIdByName("PLATFORM_ADMIN");

            dao.assignRole(userId, roleId);

            resp.sendRedirect(
                req.getContextPath()+"/superadmin/platform-admins"
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
