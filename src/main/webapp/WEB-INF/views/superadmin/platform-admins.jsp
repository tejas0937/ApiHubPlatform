<%@ page import="java.util.*,com.apihub.model.User"%>

<html>
<head>
<title>Platform Admins</title>
<style>
body{background:black;color:white;font-family:Segoe UI;padding:40px;}
table{width:100%;border-collapse:collapse;}
th,td{padding:10px;border-bottom:1px solid #333;}
input,button{padding:6px;}
</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>

<h2>Platform Admins</h2>

<h3>Add platform admin</h3>

<form method="post">

<input name="name" placeholder="Full name" required>
<input name="email" placeholder="Email" required>
<input name="password" placeholder="Password" required>
<button type="submit">Create</button>

</form>

<br><br>

<table>
<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
List<User> list =
    (List<User>) request.getAttribute("admins");

if(list!=null){
for(User u : list){
%>

<tr>
<td><%=u.getId()%></td>
<td><%=u.getFullName()%></td>
<td><%=u.getEmail()%></td>
<td><%=u.getStatus()%></td>
<td>
<form method="post"
 action="<%=request.getContextPath()%>/super/delete-platform-admin"
 style="display:inline">

<input type="hidden" name="id" value="<%=u.getId()%>">
<button type="submit">Delete</button>

</form>
</td>
</tr>

<%}}%>

</table>

</body>
</html>
