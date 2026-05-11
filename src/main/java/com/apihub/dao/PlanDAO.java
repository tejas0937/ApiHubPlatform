package com.apihub.dao;

import java.sql.*;
import java.util.*;

import com.apihub.model.Plan;

public class PlanDAO {

    private Connection con;

    public PlanDAO(Connection con) {
        this.con = con;
    }

    public long insert(Plan plan) throws Exception {

        String sql =
            "INSERT INTO plans (name, price, billing_cycle, status) VALUES (?,?,?,?)";

        try (PreparedStatement ps =
                 con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, plan.getName());
            ps.setDouble(2, plan.getPrice());
            ps.setString(3, plan.getBillingCycle());
            ps.setString(4, plan.getStatus());

            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next())
                    return rs.getLong(1);
            }
        }

        throw new Exception("Plan not created");
    }

    public void attachApi(long planId, long apiId) throws Exception {

        String sql =
            "INSERT INTO plan_api_services (plan_id, api_service_id) VALUES (?,?)";

        try(PreparedStatement ps = con.prepareStatement(sql)){

            ps.setLong(1, planId);
            ps.setLong(2, apiId);
            ps.executeUpdate();
        }
    }

    public List<Plan> findAll() throws Exception {

        List<Plan> list = new ArrayList<>();

        String sql =
            "SELECT id,name,price,billing_cycle,status,created_at FROM plans";

        try(PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                Plan p = new Plan();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setBillingCycle(rs.getString("billing_cycle"));
                p.setStatus(rs.getString("status"));
                p.setCreatedAt(
                    rs.getTimestamp("created_at").toLocalDateTime()
                );

                list.add(p);
            }
        }

        return list;
    }
}
