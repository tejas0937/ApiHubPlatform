<%@ page import="com.apihub.model.User,java.util.*" %>

<%
User navUser = (User) session.getAttribute("user");
List<String> navRoles = (List<String>) session.getAttribute("roles");
%>

<div style="display:flex;justify-content:space-between;
padding:14px 30px;background:#0c0c0c;border-bottom:1px solid #222">

<div style="font-size:20px;font-weight:bold;color:white">
APIHUB
</div>

<div style="display:flex;gap:20px;align-items:center">

<% if(navUser != null){ %>

    <% if(navRoles!=null && navRoles.contains("SUPER_ADMIN")){ %>
        <a href="<%=request.getContextPath()%>/super/dashboard" style="color:white">Dashboard</a>
    <% } %>

    <% if(navRoles!=null && navRoles.contains("PLATFORM_ADMIN")){ %>
        <a href="<%=request.getContextPath()%>/platform/dashboard" style="color:white">Dashboard</a>
    <% } %>

    <% if(navRoles!=null && navRoles.contains("ORG_ADMIN")){ %>
        <a href="<%=request.getContextPath()%>/org/dashboard" style="color:white">Dashboard</a>
    <% } %>

    <span style="color:#aaa">
        <%= navUser.getEmail() %>
    </span>

    <a href="<%=request.getContextPath()%>/logout" style="color:#ff6666">
        Logout
    </a>

<% }else{ %>

    <a href="<%=request.getContextPath()%>/login-page" style="color:white">
        Login
    </a>

<% } %>

</div>
</div>
