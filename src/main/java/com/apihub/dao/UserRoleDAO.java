package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserRoleDAO {

    private Connection con;

    public UserRoleDAO(Connection con) {
        this.con = con;
    }

    public void assignRole(long userId, long roleId) throws Exception {

        String sql =
                "INSERT INTO user_roles(user_id,role_id) VALUES(?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, userId);
            ps.setLong(2, roleId);

            ps.executeUpdate();
        }
    }
}
