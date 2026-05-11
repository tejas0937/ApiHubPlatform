<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,com.apihub.model.ApiService"%>

<html>
<head>
<title>API Services</title>
<style>
body{background:#000;color:#fff;font-family:Segoe UI;padding:40px;}
table{width:100%;border-collapse:collapse;margin-top:20px;}
th,td{padding:10px;border-bottom:1px solid #333;}
input,textarea,select,button{padding:8px;margin-top:6px;}
</style>
</head>
<body>

<h2>Add API Service</h2>

<form method="post"
 action="<%=request.getContextPath()%>/platform/add-api">

<input type="text" name="name" placeholder="API name" required><br>
<textarea name="description" placeholder="Description"></textarea><br>

<select name="status">
    <option value="ACTIVE">ACTIVE</option>
    <option value="INACTIVE">INACTIVE</option>
</select><br>

<button type="submit">Add API</button>
</form>

<hr>

<h2>All API Services</h2>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Description</th>
<th>Status</th>
</tr>

<%
List<ApiService> list =
        (List<ApiService>) request.getAttribute("apis");

if(list!=null){
for(ApiService a:list){
%>

<tr>
<td><%=a.getId()%></td>
<td><%=a.getName()%></td>
<td><%=a.getDescription()%></td>
<td><%=a.getStatus()%></td>
</tr>

<%}} %>

</table>

</body>
</html>
