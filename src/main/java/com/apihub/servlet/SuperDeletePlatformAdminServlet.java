package com.apihub.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.apihub.dao.UserDAO;
import com.apihub.util.DBUtil;

@WebServlet("/superadmin/delete-platform-admin")
public class SuperDeletePlatformAdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        List<String> roles =
            (List<String>) session.getAttribute("roles");

        if(roles==null || !roles.contains("SUPER_ADMIN")){
            resp.setStatus(403); return;
        }

        long id = Long.parseLong(req.getParameter("id"));

        try(Connection con = DBUtil.getConnection()){

            UserDAO dao = new UserDAO(con);
            dao.deleteUser(id);

            resp.sendRedirect(
                req.getContextPath()+"/superadmin/platform-admins"
            );

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
