package com.apihub.service;

import com.apihub.dao.UserDAO;
import com.apihub.dao.UserRoleReadDAO;
import com.apihub.model.User;
import com.apihub.util.DBUtil;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginService {

    public Map<String, Object> login(String email, String password)
            throws Exception {

        try (Connection con = DBUtil.getConnection()) {

            UserDAO userDAO = new UserDAO(con);

            User user = userDAO.findByEmail(email);

            if (user == null) return null;

            String hash = Integer.toHexString(password.hashCode());

            if (!hash.equals(user.getPasswordHash())) return null;

            UserRoleReadDAO roleDAO = new UserRoleReadDAO(con);

            List<String> roles =
                    roleDAO.findRoleNamesByUserId(user.getId());

            Map<String, Object> result = new HashMap<>();
            result.put("user", user);
            result.put("roles", roles);

            return result;
        }
    }
}
