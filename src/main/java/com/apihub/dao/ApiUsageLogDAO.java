package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.apihub.model.ApiUsageLog;

public class ApiUsageLogDAO {

    private Connection con;

    public ApiUsageLogDAO(Connection con) {
        this.con = con;
    }

    public void insert(ApiUsageLog log) throws Exception {

        String sql =
            "INSERT INTO api_usage_logs (api_key_id, api_name) VALUES (?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, log.getApiKeyId());
            ps.setString(2, log.getApiName());

            ps.executeUpdate();
        }
    }

    // ✅ Count total usage for quota validation
    public int countUsageByApiKey(long apiKeyId) throws Exception {

        String sql =
            "SELECT COUNT(*) FROM api_usage_logs WHERE api_key_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, apiKeyId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }
}