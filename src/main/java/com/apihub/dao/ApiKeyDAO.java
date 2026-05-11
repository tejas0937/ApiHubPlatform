package com.apihub.dao;
import java.util.*;
import java.sql.*;

import com.apihub.model.ApiKey;

public class ApiKeyDAO {

    private Connection con;

    public ApiKeyDAO(Connection con) {
        this.con = con;
    }
    public List<ApiKey> findByOrganization(long orgId) throws Exception {

        List<ApiKey> list = new ArrayList<>();

        String sql =
            "SELECT id, organization_id, subscription_id, api_key, status, created_at " +
            "FROM api_keys WHERE organization_id=?";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setLong(1, orgId);

            try(ResultSet rs = ps.executeQuery()){

                while(rs.next()){

                    ApiKey k = new ApiKey();
                    k.setId(rs.getLong("id"));
                    k.setOrganizationId(rs.getLong("organization_id"));
                    k.setSubscriptionId(rs.getLong("subscription_id"));
                    k.setApiKey(rs.getString("api_key"));
                    k.setStatus(rs.getString("status"));
                    k.setCreatedAt(
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );

                    list.add(k);
                }
            }
        }

        return list;
    }

    public void insert(ApiKey key) throws Exception {

        String sql =
            "INSERT INTO api_keys " +
            "(organization_id, subscription_id, api_key, status) " +
            "VALUES (?,?,?,?)";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setLong(1, key.getOrganizationId());
            ps.setLong(2, key.getSubscriptionId());
            ps.setString(3, key.getApiKey());
            ps.setString(4, key.getStatus());

            ps.executeUpdate();
        }
    }

    public ApiKey findByKey(String apiKey) throws Exception {

        String sql =
            "SELECT id, organization_id, subscription_id, api_key, status, created_at " +
            "FROM api_keys WHERE api_key=? AND status='ACTIVE'";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, apiKey);

            try(ResultSet rs = ps.executeQuery()){

                if(rs.next()){

                    ApiKey k = new ApiKey();
                    k.setId(rs.getLong("id"));
                    k.setOrganizationId(rs.getLong("organization_id"));
                    k.setSubscriptionId(rs.getLong("subscription_id"));
                    k.setApiKey(rs.getString("api_key"));
                    k.setStatus(rs.getString("status"));
                    k.setCreatedAt(
                        rs.getTimestamp("created_at").toLocalDateTime()
                    );

                    return k;
                }
            }
        }

        return null;
    }
}
