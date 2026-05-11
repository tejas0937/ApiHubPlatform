package com.apihub.service;

import java.sql.Connection;

import com.apihub.dao.OrganizationDAO;
import com.apihub.dao.UserDAO;
import com.apihub.model.Organization;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

public class OrganizationRegistrationService {

    public void registerOrganizationWithAdmin(
            String orgName,
            String orgEmail,
            String adminName,
            String adminEmail,
            String password
    ) throws Exception {

        try (Connection con = DBUtil.getConnection()) {

            con.setAutoCommit(false);

            OrganizationDAO organizationDAO = new OrganizationDAO(con);
            UserDAO userDAO = new UserDAO(con);

            // 1. Create organization
            Organization org = new Organization();
            org.setName(orgName);
            org.setEmail(orgEmail);
            org.setStatus("ACTIVE");

            long orgId = organizationDAO.insert(org);   // <<< FIXED

            // 2. Create org admin user
            User admin = new User();
            admin.setOrganizationId(orgId);
            admin.setFullName(adminName);
            admin.setEmail(adminEmail);
            admin.setPassword(password);   // must map to your real password column
            admin.setStatus("ACTIVE");

            userDAO.insert(admin);

            // 3. Assign ORG_ADMIN role
            userDAO.assignRole(admin.getEmail(), "ORG_ADMIN");

            con.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
