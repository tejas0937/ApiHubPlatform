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

    public List<ApiService> findAll() throws Exception {

        List<ApiService> list = new ArrayList<>();

        String sql =
            "SELECT id, name, description, status, endpoint, http_method, request_json, response_json " +
            "FROM api_services " +
            "ORDER BY name";

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                ApiService api = new ApiService();

                api.setId(rs.getLong("id"));
                api.setName(rs.getString("name"));
                api.setDescription(rs.getString("description"));
                api.setStatus(rs.getString("status"));
                api.setEndpoint(rs.getString("endpoint"));
                api.setHttpMethod(rs.getString("http_method"));
                api.setRequestJson(rs.getString("request_json"));
                api.setResponseJson(rs.getString("response_json"));

                list.add(api);
            }
        }

        return list;
    }
}