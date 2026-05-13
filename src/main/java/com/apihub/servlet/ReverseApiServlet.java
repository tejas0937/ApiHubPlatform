package com.apihub.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/reverse")
public class ReverseApiServlet extends BaseApiServlet {

    @Override
    protected String getApiName() {
        return "Reverse";
    }

    @Override
    protected void processRequest(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {

        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader reader =
                req.getReader()) {

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String body = sb.toString();

        String text = "";

        if (body.contains("text")) {

            try {

                text = body.split(":")[1]
                           .replace("\"", "")
                           .replace("}", "")
                           .trim();

            } catch (Exception e) {

                text = "";
            }
        }

        String reversed =
                new StringBuilder(text)
                        .reverse()
                        .toString();

        resp.setContentType("application/json");

        resp.getWriter().write(
            "{ \"result\": \"" + reversed + "\" }"
        );
    }

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
}