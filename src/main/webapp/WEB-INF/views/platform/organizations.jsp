<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.apihub.model.Organization" %>

<!DOCTYPE html>
<html>
<head>
<title>Organizations - ApiHub</title>
<style>
body{background:#000;color:#fff;font-family:Segoe UI;padding:40px;}
table{width:100%;border-collapse:collapse;}
th,td{padding:12px;border-bottom:1px solid #333;}
</style>
</head>
<body>

<h2>Registered Organizations</h2><br>

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Status</th>
    <th>Created</th>
</tr>

<%
List<Organization> list =
        (List<Organization>) request.getAttribute("organizations");

if(list != null){
    for(Organization o : list){
%>

<tr>
    <td><%=o.getId()%></td>
    <td><%=o.getName()%></td>
    <td><%=o.getEmail()%></td>
    <td><%=o.getStatus()%></td>
    <td><%=o.getCreatedAt()%></td>
</tr>

<%
    }
}
%>

</table>

</body>
</html>
