package com.apihub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.apihub.model.Subscription;

public class SubscriptionDAO {

    private Connection con;

    public SubscriptionDAO(Connection con) {
        this.con = con;
    }

    public void create(Subscription s) throws Exception {

        String sql =
            "INSERT INTO subscriptions " +
            "(organization_id, plan_id, start_date, status) " +
            "VALUES (?,?,?,?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, s.getOrganizationId());
            ps.setLong(2, s.getPlanId());
            ps.setDate(3, java.sql.Date.valueOf(s.getStartDate()));
            ps.setString(4, s.getStatus());

            ps.executeUpdate();
        }
    }
}
