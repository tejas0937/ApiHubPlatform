<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,com.apihub.model.Plan"%>

<html>
<head>
<title>Choose Plan</title>
<style>
body{background:#000;color:#fff;font-family:Segoe UI;padding:40px;}
table{width:100%;border-collapse:collapse;}
th,td{padding:10px;border-bottom:1px solid #333;}
button{padding:6px 12px;}
</style>
</head>
<body>

<h2>Available Plans</h2>

<table>
<tr>
<th>Name</th>
<th>Price</th>
<th>Cycle</th>
<th>Action</th>
</tr>

<%
List<Plan> plans =
        (List<Plan>) request.getAttribute("plans");

if(plans!=null){
for(Plan p:plans){
%>

<tr>
<td><%=p.getName()%></td>
<td><%=p.getPrice()%></td>
<td><%=p.getBillingCycle()%></td>
<td>
<form method="post"
 action="<%=request.getContextPath()%>/org/subscribe">
    <input type="hidden" name="planId" value="<%=p.getId()%>">
    <button type="submit">Subscribe</button>
</form>
</td>
</tr>

<% }} %>

</table>

</body>
</html>
