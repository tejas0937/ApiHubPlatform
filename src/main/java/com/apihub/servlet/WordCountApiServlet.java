package com.apihub.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/wordcount")
public class WordCountApiServlet extends BaseApiServlet {

    @Override
    protected String getApiName() {
        return "WordCount";
    }

    @Override
    protected void processRequest(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws Exception {

        String body =
            (String) req.getAttribute("requestBody");

        String text = "";

        if (body != null && body.contains("text")) {

            try {

            	text = body.substring(
                        body.indexOf(":") + 1
                   )
                   .replace("\"", "")
                   .replace("}", "")
                   .replace("{", "")
                   .trim();
            	
            } catch (Exception e) {

                text = "";
            }
        }

        int wordCount = 0;

        if (!text.isEmpty()) {

            wordCount =
                text.trim()
                    .split("\\s+")
                    .length;
        }

        resp.setContentType("application/json");

        resp.getWriter().write(
            "{ \"words\": " + wordCount + " }"
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