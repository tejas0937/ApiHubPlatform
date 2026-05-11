package com.apihub.service;
import java.util.*;
import com.apihub.dao.RoleDAO;
import com.apihub.dao.UserDAO;
import com.apihub.dao.UserRoleDAO;
import com.apihub.model.Role;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

import java.sql.Connection;
import java.time.LocalDateTime;

public class OrgEmployeeService {
	public List<User> getEmployeesByOrganization(long orgId) throws Exception {

	    try (Connection con = DBUtil.getConnection()) {

	        UserDAO userDAO = new UserDAO(con);

	        return userDAO.findByOrganization(orgId);
	    }
	}

    public void addEmployee(long orgId,
                            String name,
                            String email,
                            String password,
                            String roleName) throws Exception {

        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false);

            try {

                UserDAO userDAO = new UserDAO(con);
                RoleDAO roleDAO = new RoleDAO(con);
                UserRoleDAO userRoleDAO = new UserRoleDAO(con);

                User user = new User();
                user.setOrganizationId(orgId);
                user.setFullName(name);
                user.setEmail(email);
                user.setPasswordHash(
                        Integer.toHexString(password.hashCode())
                );
                user.setStatus("ACTIVE");
                user.setCreatedAt(LocalDateTime.now());

                long userId = userDAO.insert(user);

                Role role = roleDAO.findByName(roleName);

                if (role == null) {
                    throw new RuntimeException("Invalid role");
                }

                userRoleDAO.assignRole(userId, role.getId());

                con.commit();

            } catch (Exception e) {
                con.rollback();
                throw e;
            }
        }
    }
}
