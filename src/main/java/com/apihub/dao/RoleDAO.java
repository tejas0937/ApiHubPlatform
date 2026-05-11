package com.apihub.dao;

import com.apihub.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleDAO {

    private Connection con;

    public RoleDAO(Connection con) {
        this.con = con;
    }

    public Role findByName(String name) throws Exception {

        String sql = "SELECT id,name FROM roles WHERE name=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Role r = new Role();
                    r.setId(rs.getLong("id"));
                    r.setName(rs.getString("name"));
                    return r;
                }
            }
        }

        return null;
    }
}
