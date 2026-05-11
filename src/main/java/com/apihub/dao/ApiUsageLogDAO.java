package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.apihub.model.ApiUsageLog;

public class ApiUsageLogDAO {

    private Connection con;

    public ApiUsageLogDAO(Connection con) {
        this.con = con;
    }

    public void insert(ApiUsageLog log) throws Exception {

        String sql =
            "INSERT INTO api_usage_logs (api_key_id, api_name) VALUES (?,?)";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setLong(1, log.getApiKeyId());
            ps.setString(2, log.getApiName());

            ps.executeUpdate();
        }
    }
}
