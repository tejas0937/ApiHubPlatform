package com.apihub.test;

import com.apihub.dao.RoleDAO;
import com.apihub.dao.UserDAO;
import com.apihub.dao.UserRoleDAO;
import com.apihub.model.Role;
import com.apihub.model.User;

import java.time.LocalDateTime;

public class SuperAdminTest {

    public static void main(String[] args) {

        try {

            UserDAO userDAO = new UserDAO();
            RoleDAO roleDAO = new RoleDAO();
            UserRoleDAO userRoleDAO = new UserRoleDAO();

            User u = new User();
            u.setOrganizationId(null);
            u.setFullName("ApiHub Super Admin");
            u.setEmail("superadmin@apihub.com");
            u.setPasswordHash("dummy");
            u.setStatus("ACTIVE");
            u.setCreatedAt(LocalDateTime.now());

            long userId = userDAO.insert(u);

            Role role = roleDAO.findByName("SUPER_ADMIN");

            userRoleDAO.assignRole(userId, role.getId());

            System.out.println("SUPER_ADMIN created with id = " + userId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
