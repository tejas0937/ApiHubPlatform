<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,com.apihub.model.*"%>

<!DOCTYPE html>
<html>
<head>
<title>Plans - ApiHub</title>

<style>
body{background:#000;color:#fff;font-family:Segoe UI;padding:40px;}
table{width:100%;border-collapse:collapse;margin-top:20px;}
th,td{padding:10px;border-bottom:1px solid #333;}
input,select,button{padding:8px;margin-top:6px;}
.api-box{margin-right:15px;}
</style>

</head>
<body>

<h2>Create Plan</h2>

<form method="post" action="<%=request.getContextPath()%>/platform/add-plan">

<input type="text" name="name" placeholder="Plan name" required><br>

<input type="number" step="0.01" name="price" placeholder="Price" required><br>

<select name="cycle" required>
    <option value="MONTHLY">MONTHLY</option>
    <option value="YEARLY">YEARLY</option>
</select>

<h4>APIs in this plan</h4>

<%
List<ApiService> apis =
        (List<ApiService>) request.getAttribute("apis");

if(apis != null){
    for(ApiService a : apis){
%>
    <label class="api-box">
        <input type="checkbox" name="apis" value="<%=a.getId()%>">
        <%=a.getName()%>
    </label>
<%
    }
}
%>

<br><br>
<button type="submit">Create Plan</button>

</form>

<hr>

<h2>All Plans</h2>

<table>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Cycle</th>
    <th>Status</th>
</tr>

<%
List<Plan> plans =
        (List<Plan>) request.getAttribute("plans");

if(plans != null){
    for(Plan p : plans){
%>
<tr>
    <td><%=p.getId()%></td>
    <td><%=p.getName()%></td>
    <td><%=p.getPrice()%></td>
    <td><%=p.getBillingCycle()%></td>
    <td><%=p.getStatus()%></td>
</tr>
<%
    }
}
%>

</table>

</body>
</html>
