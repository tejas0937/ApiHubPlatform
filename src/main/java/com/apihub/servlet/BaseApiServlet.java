package com.apihub.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apihub.dao.ApiKeyDAO;
import com.apihub.dao.ApiUsageLogDAO;
import com.apihub.dao.PlanDAO;
import com.apihub.model.ApiKey;
import com.apihub.model.ApiUsageLog;
import com.apihub.util.DBUtil;

public abstract class BaseApiServlet extends HttpServlet {

    protected abstract String getApiName();

    protected abstract void processRequest(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception;

    protected void handle(HttpServletRequest req,
                          HttpServletResponse resp)
            throws IOException {

        String apiKey = req.getHeader("MyApi");

        // ✅ Always read request body

        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader reader =
                req.getReader()) {

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String body = sb.toString();

        // ✅ Store request body for child APIs

        req.setAttribute("requestBody", body);

        // ✅ fallback apiKey from JSON body

        if ((apiKey == null || apiKey.trim().isEmpty())
                && body.contains("apiKey")) {

            try {

                apiKey = body.substring(
                            body.indexOf(":") + 1
                         )
                         .replace("\"", "")
                         .replace("}", "")
                         .replace("{", "")
                         .trim();

            } catch (Exception e) {

                apiKey = null;
            }
        }

        // ❌ Missing API key

        if (apiKey == null || apiKey.trim().isEmpty()) {

            resp.setStatus(401);

            resp.getWriter().write(
                "Missing API key"
            );

            return;
        }

        try (Connection con = DBUtil.getConnection()) {

            ApiKeyDAO keyDao =
                    new ApiKeyDAO(con);

            ApiKey key =
                    keyDao.findByKey(apiKey);

            // ❌ Invalid key

            if (key == null) {

                resp.setStatus(401);

                resp.getWriter().write(
                    "Invalid API key"
                );

                return;
            }

            // ❌ Revoked key

            if (!"ACTIVE".equals(key.getStatus())) {

                resp.setStatus(403);

                resp.getWriter().write(
                    "API key revoked"
                );

                return;
            }

            // ✅ Quota validation

            ApiUsageLogDAO usageDao =
                    new ApiUsageLogDAO(con);

            int currentUsage =
                    usageDao.countUsageByApiKey(
                            key.getId()
                    );

            PlanDAO planDAO =
                    new PlanDAO(con);

            int requestLimit =
                    planDAO.getRequestLimitBySubscription(
                            key.getSubscriptionId()
                    );

            if (currentUsage >= requestLimit) {

                resp.setStatus(429);

                resp.setContentType(
                    "application/json"
                );

                resp.getWriter().write(
                    "{ \"error\": \"API quota exceeded\" }"
                );

                return;
            }

            // ✅ Actual API processing

            processRequest(req, resp);

            // ✅ Usage logging

            ApiUsageLog log =
                    new ApiUsageLog();

            log.setApiKeyId(key.getId());
            log.setApiName(getApiName());

            usageDao.insert(log);

        } catch (Exception e) {

            e.printStackTrace();

            resp.setStatus(500);

            resp.getWriter().write(
                "Server error"
            );
        }
    }
}