package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRoleReadDAO {

    private Connection con;

    public UserRoleReadDAO(Connection con) {
        this.con = con;
    }

    public List<String> findRoleNamesByUserId(long userId) throws Exception {

        List<String> roles = new ArrayList<>();

        String sql =
            "SELECT r.name " +
            "FROM user_roles ur " +
            "JOIN roles r ON ur.role_id = r.id " +
            "WHERE ur.user_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    roles.add(rs.getString("name"));
                }
            }
        }

        return roles;
    }
}
