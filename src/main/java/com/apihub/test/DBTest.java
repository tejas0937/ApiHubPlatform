package com.apihub.test;

import java.sql.Connection;
import com.apihub.util.DBUtil;

public class DBTest {

    public static void main(String[] args) {

        try (Connection con = DBUtil.getConnection()) {
            System.out.println("ApiHub database connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
