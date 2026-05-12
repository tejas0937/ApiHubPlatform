package com.apihub.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apihub.dao.ApiKeyDAO;
import com.apihub.dao.ApiUsageLogDAO;
import com.apihub.dao.PlanDAO;
import com.apihub.model.ApiKey;
import com.apihub.model.ApiUsageLog;
import com.apihub.util.DBUtil;

@WebServlet("/api/demotest")
public class DemoApiTest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        handle(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        handle(req, resp);
    }

    private void handle(HttpServletRequest req,
                        HttpServletResponse resp)
            throws IOException {

        String apiKey = null;

        // ✅ Read API key from header
        apiKey = req.getHeader("MyApi");

        // ✅ If not found, read from JSON body
        if (apiKey == null || apiKey.trim().isEmpty()) {

            StringBuilder sb = new StringBuilder();
            String line;

            try (BufferedReader reader = req.getReader()) {

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            String body = sb.toString();

            if (body.contains("apiKey")) {

                try {

                    apiKey = body.split(":")[1]
                                 .replace("\"", "")
                                 .replace("}", "")
                                 .trim();

                } catch (Exception e) {

                    apiKey = null;
                }
            }
        }

        // ❌ Missing API key
        if (apiKey == null || apiKey.trim().isEmpty()) {

            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Missing API key");
            return;
        }

        try (Connection con = DBUtil.getConnection()) {

            ApiKeyDAO keyDao = new ApiKeyDAO(con);

            ApiKey key = keyDao.findByKey(apiKey);

            // ❌ Invalid API key
            if (key == null) {

                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("Invalid API key");
                return;
            }

            // ✅ QUOTA VALIDATION

            ApiUsageLogDAO usageDao =
                    new ApiUsageLogDAO(con);

            int currentUsage =
                    usageDao.countUsageByApiKey(
                            key.getId()
                    );

            PlanDAO planDAO = new PlanDAO(con);

            int requestLimit =
                    planDAO.getRequestLimitBySubscription(
                            key.getSubscriptionId()
                    );

            // ❌ Quota exceeded
            if (currentUsage >= requestLimit) {

                resp.setStatus(429);

                resp.setContentType("application/json");

                resp.getWriter().write(
                    "{ \"error\": \"API quota exceeded\" }"
                );

                return;
            }

            // ✅ Log API usage

            ApiUsageLog log = new ApiUsageLog();
            log.setApiKeyId(key.getId());
            log.setApiName("Demo");

            usageDao.insert(log);

            // ✅ Success response

            resp.setContentType("application/json");

            resp.getWriter().write(
                "{ \"message\": \"Api processed successfully\" }"
            );

        } catch (Exception e) {

            e.printStackTrace();

            resp.setStatus(500);

            resp.getWriter().write("Server error");
        }
    }
}