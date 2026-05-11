package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.apihub.model.ApiService;

public class ApiServiceDAO {

    private Connection con;

    public ApiServiceDAO(Connection con) {
        this.con = con;
    }

    // add api service
    public void insert(ApiService api) throws Exception {

        String sql =
            "INSERT INTO api_services (name, description, status) " +
            "VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, api.getName());
            ps.setString(2, api.getDescription());
            ps.setString(3, api.getStatus());

            ps.executeUpdate();
        }
    }

    // list all api services
    public List<ApiService> findAll() throws Exception {

        List<ApiService> list = new ArrayList<>();

        String sql =
            "SELECT id, name, description, status FROM api_services";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                ApiService api = new ApiService();

                api.setId(rs.getLong("id"));
                api.setName(rs.getString("name"));
                api.setDescription(rs.getString("description"));
                api.setStatus(rs.getString("status"));

                list.add(api);
            }
        }

        return list;
    }
}
