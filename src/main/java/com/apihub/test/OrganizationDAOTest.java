package com.apihub.test;

import com.apihub.dao.OrganizationDAO;
import com.apihub.model.Organization;

import java.time.LocalDateTime;

public class OrganizationDAOTest {

    public static void main(String[] args) {

        try {

            OrganizationDAO dao = new OrganizationDAO();

            Organization org = new Organization();
            org.setName("Demo Company");
            org.setEmail("demo@company.com");
            org.setStatus("ACTIVE");
            org.setCreatedAt(LocalDateTime.now());

            long id = dao.insert(org);

            System.out.println("Inserted organization id = " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
