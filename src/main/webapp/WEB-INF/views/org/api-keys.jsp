<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,com.apihub.model.ApiKey"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>API Keys</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:"Segoe UI",Arial,sans-serif;
}

body{
    background:#000;
    color:#fff;
    overflow-x:hidden;
}

.page{
    padding:50px;
    max-width:1200px;
    margin:auto;
}

h2{
    font-size:34px;
    margin-bottom:30px;
}

/* generate form */

.form-box{
    border:1px solid #222;
    border-radius:12px;
    padding:25px;
    background:#0a0a0a;
    margin-bottom:40px;
}

.form-box h3{
    margin-bottom:20px;
    font-size:20px;
}

input{
    width:100%;
    padding:14px;
    border:1px solid #333;
    border-radius:8px;
    background:#111;
    color:white;
    margin-bottom:18px;
}

button{
    padding:12px 18px;
    border:none;
    border-radius:8px;
    cursor:pointer;
    font-weight:600;
}

/* primary button */

.primary-btn{
    background:white;
    color:black;
}

/* table */

.table-box{
    border:1px solid #222;
    border-radius:12px;
    overflow:hidden;
    background:#0a0a0a;
}

table{
    width:100%;
    border-collapse:collapse;
}

th{
    text-align:left;
    padding:18px;
    border-bottom:1px solid #222;
    color:#888;
    font-size:13px;
    letter-spacing:1px;
}

td{
    padding:18px;
    border-bottom:1px solid #111;
    font-size:14px;
}

tr:hover{
    background:#111;
}

.key-text{
    font-size:12px;
    word-break:break-all;
    color:#ddd;
}

/* statuses */

.status-active{
    color:#4ade80;
    font-weight:bold;
}

.status-revoked{
    color:#f87171;
    font-weight:bold;
}

/* action buttons */

.action-form{
    display:inline;
}

.revoke-btn{
    background:#ef4444;
    color:white;
}

.activate-btn{
    background:#22c55e;
    color:black;
}

.empty{
    text-align:center;
    color:#777;
    padding:35px;
}

/* background glow */

.light-top{
    position:fixed;
    top:-220px;
    right:-220px;
    width:520px;
    height:520px;
    background:radial-gradient(circle,
        rgba(200,200,200,.18),
        transparent 65%);
    pointer-events:none;
}

.light-bottom{
    position:fixed;
    bottom:-220px;
    left:-220px;
    width:520px;
    height:520px;
    background:radial-gradient(circle,
        rgba(160,160,160,.18),
        transparent 65%);
    pointer-events:none;
}

</style>
</head>

<body>

<div class="light-top"></div>
<div class="light-bottom"></div>

<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>

<div class="page">

<h2>Manage API Keys</h2>

<!-- generate api key -->

<div class="form-box">

<h3>Generate New API Key</h3>

<form method="post"
      action="<%=request.getContextPath()%>/org/generate-key">

<input name="subscriptionId"
       placeholder="Enter Subscription ID"
       required>

<button type="submit"
        class="primary-btn">

    Generate API Key

</button>

</form>

</div>

<!-- api keys table -->

<div class="table-box">

<table>

<tr>
    <th>ID</th>
    <th>Subscription</th>
    <th>API Key</th>
    <th>Status</th>
    <th>Created</th>
    <th>Action</th>
</tr>

<%

List<ApiKey> list =
    (List<ApiKey>) request.getAttribute("keys");

if(list != null && !list.isEmpty()){

    for(ApiKey k : list){
%>

<tr>

<td>
    <%= k.getId() %>
</td>

<td>
    <%= k.getSubscriptionId() %>
</td>

<td class="key-text">
    <%= k.getApiKey() %>
</td>

<td>

<%
if("ACTIVE".equals(k.getStatus())){
%>

<span class="status-active">
    ACTIVE
</span>

<%
}else{
%>

<span class="status-revoked">
    REVOKED
</span>

<%
}
%>

</td>

<td>
    <%= k.getCreatedAt() %>
</td>

<td>

<form class="action-form"
      method="post"
      action="<%=request.getContextPath()%>/org/api-keys">

<input type="hidden"
       name="keyId"
       value="<%=k.getId()%>">

<%

if("ACTIVE".equals(k.getStatus())){
%>

<input type="hidden"
       name="status"
       value="REVOKED">

<button type="submit"
        class="revoke-btn">

    Revoke

</button>

<%
}else{
%>

<input type="hidden"
       name="status"
       value="ACTIVE">

<button type="submit"
        class="activate-btn">

    Activate

</button>

<%
}
%>

</form>

</td>

</tr>

<%
    }

}else{
%>

<tr>

<td colspan="6"
    class="empty">

    No API keys found

</td>

</tr>

<%
}
%>

</table>

</div>

</div>

</body>
</html>