package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.apihub.model.Organization;

public class OrganizationDAO {

    private Connection con;

    public OrganizationDAO(Connection con) {
        this.con = con;
    }

    // INSERT organization and RETURN generated id
    public long insert(Organization org) throws Exception {

        String sql =
            "INSERT INTO organizations (name, email, status) VALUES (?, ?, ?)";

        try (PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, org.getName());
            ps.setString(2, org.getEmail());
            ps.setString(3, org.getStatus());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }

        throw new Exception("Failed to create organization");
    }

    // ✅ For PLATFORM ADMIN – list all organizations
    public List<Organization> findAll() throws Exception {

        List<Organization> list = new ArrayList<>();

        String sql =
            "SELECT id, name, email, status, created_at FROM organizations";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Organization org = new Organization();

                org.setId(rs.getLong("id"));
                org.setName(rs.getString("name"));
                org.setEmail(rs.getString("email"));
                org.setStatus(rs.getString("status"));

                Timestamp createdAt = rs.getTimestamp("created_at");
                org.setCreatedAt(
                        rs.getTimestamp("created_at").toLocalDateTime()
                );


                list.add(org);
            }
        }

        return list;
    }
}
