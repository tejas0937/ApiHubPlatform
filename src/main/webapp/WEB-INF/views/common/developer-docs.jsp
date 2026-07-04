<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*,com.apihub.model.ApiService"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ApiHub Developer Documentation</title>

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
}

.page{
    max-width:1200px;
    margin:auto;
    padding:50px;
}

.header{
    text-align:center;
    margin-bottom:50px;
}

.header h1{
    font-size:42px;
    margin-bottom:15px;
}

.header p{
    color:#888;
    font-size:18px;
}

.api-card{

    border:1px solid #222;
    border-radius:14px;
    padding:28px;
    margin-bottom:35px;

    background:#0d0d0d;

    transition:.25s;
}

.api-card:hover{

    border-color:#555;
    transform:translateY(-4px);
}

.title{

    display:flex;
    justify-content:space-between;
    align-items:center;

    margin-bottom:20px;
}

.title h2{

    font-size:28px;
}

.status{

    padding:8px 16px;

    border-radius:30px;

    background:#16a34a;

    font-size:13px;

    font-weight:bold;
}

.label{

    color:#999;

    font-size:14px;

    margin-top:20px;

    margin-bottom:8px;
}

.box{

    background:#111;

    border:1px solid #222;

    border-radius:8px;

    padding:15px;

    white-space:pre-wrap;

    font-family:Consolas,monospace;

    color:#d6d6d6;
}

.method{

    display:inline-block;

    background:#2563eb;

    padding:7px 15px;

    border-radius:6px;

    margin-right:12px;

    font-size:13px;

    font-weight:bold;
}

.endpoint{

    color:#60a5fa;

    font-family:Consolas;

    font-size:15px;
}

.description{

    margin-top:15px;

    color:#bbb;

    line-height:1.7;
}

</style>

</head>

<body>

<div class="page">

<div class="header">

<h1>Developer API Documentation</h1>

<p>
Explore all available APIs provided by ApiHub.
</p>

</div>

<%

List<ApiService> apis =
(List<ApiService>)request.getAttribute("apis");

if(apis!=null){

for(ApiService api : apis){

%>

<div class="api-card">

<div class="title">

<h2><%=api.getName()%></h2>

<span class="status">

<%=api.getStatus()%>

</span>

</div>

<div>

<span class="method">

<%=api.getHttpMethod()%>

</span>

<span class="endpoint">

<%=request.getContextPath()%><%=api.getEndpoint()%>

</span>

</div>

<p class="description">

<%=api.getDescription()%>

</p>

<div class="label">

Request JSON

</div>

<div class="box">

<%=api.getRequestJson()%>

</div>

<div class="label">

Response JSON

</div>

<div class="box">

<%=api.getResponseJson()%>

</div>

</div>

<%

}

}

%>

</div>

</body>
</html>