package com.apihub.dao;

import java.sql.*;
import java.util.*;

import com.apihub.model.ApiUsageLog;

public class ApiAnalyticsDAO {

    private Connection con;

    public ApiAnalyticsDAO(Connection con) {
        this.con = con;
    }

    // Total API calls
    public int countTotalApiCalls(long orgId)
            throws Exception {

        String sql =
            "SELECT COUNT(*) " +
            "FROM api_usage_logs l " +
            "JOIN api_keys k ON l.api_key_id = k.id " +
            "WHERE k.organization_id = ?";

        try (PreparedStatement ps =
                con.prepareStatement(sql)) {

            ps.setLong(1, orgId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

    // Total API keys
    public int countApiKeys(long orgId)
            throws Exception {

        String sql =
            "SELECT COUNT(*) FROM api_keys " +
            "WHERE organization_id = ?";

        try (PreparedStatement ps =
                con.prepareStatement(sql)) {

            ps.setLong(1, orgId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

    // Usage logs
    public List<ApiUsageLog> getUsageLogs(long orgId)
            throws Exception {

        List<ApiUsageLog> list = new ArrayList<>();

        String sql =
            "SELECT l.api_name, l.api_key_id, " +
            "k.subscription_id, COUNT(*) AS hits " +
            "FROM api_usage_logs l " +
            "JOIN api_keys k ON l.api_key_id = k.id " +
            "WHERE k.organization_id = ? " +
            "GROUP BY l.api_key_id, l.api_name, k.subscription_id";

        try (PreparedStatement ps =
                con.prepareStatement(sql)) {

            ps.setLong(1, orgId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ApiUsageLog log =
                            new ApiUsageLog();

                    log.setApiName(
                        rs.getString("api_name")
                    );

                    log.setApiKeyId(
                        rs.getLong("api_key_id")
                    );

                    log.setSubscriptionId(
                        rs.getLong("subscription_id")
                    );

                    log.setHits(
                        rs.getInt("hits")
                    );

                    list.add(log);
                }
            }
        }

        return list;
    }
}