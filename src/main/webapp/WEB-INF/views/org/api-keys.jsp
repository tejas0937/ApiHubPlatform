<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,com.apihub.model.ApiKey"%>

<html>
<head>
<title>API Keys</title>
<style>
body{background:black;color:white;font-family:Segoe UI;padding:40px;}
table{width:100%;border-collapse:collapse;}
th,td{padding:10px;border-bottom:1px solid #333;}
button{padding:6px 12px;}
</style>
</head>
<body>

<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>

<h2>API Keys</h2>

<!-- temporary simple generation (latest subscription id manually) -->

<form method="post"
 action="<%=request.getContextPath()%>/org/generate-key">

<input name="subscriptionId"
       placeholder="Subscription ID"
       required>

<button type="submit">Generate API key</button>

</form>

<br><br>

<table>
<tr>
<th>ID</th>
<th>Subscription</th>
<th>API Key</th>
<th>Status</th>
<th>Created</th>
</tr>

<%
List<ApiKey> list =
    (List<ApiKey>) request.getAttribute("keys");

if(list!=null){
for(ApiKey k:list){
%>

<tr>
<td><%=k.getId()%></td>
<td><%=k.getSubscriptionId()%></td>
<td style="font-size:12px"><%=k.getApiKey()%></td>
<td><%=k.getStatus()%></td>
<td><%=k.getCreatedAt()%></td>
</tr>

<% }} %>

</table>

</body>
</html>
