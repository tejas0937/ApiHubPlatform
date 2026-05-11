<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>ApiHub – Platform Admin</title>

<style>
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif;}
body{background:#000;color:#fff;overflow-x:hidden;}

.light-top{
 position:fixed;top:-220px;right:-220px;width:520px;height:520px;
 background:radial-gradient(circle,rgba(200,200,200,.18),transparent 65%);
 pointer-events:none;
}
.light-bottom{
 position:fixed;bottom:-220px;left:-220px;width:520px;height:520px;
 background:radial-gradient(circle,rgba(160,160,160,.18),transparent 65%);
 pointer-events:none;
}

.page{
 min-height:78vh;
 display:flex;
 flex-direction:column;
 align-items:center;
 justify-content:center;
 padding:60px 20px;
 text-align:center;
}

.page h1{
 font-size:40px;
 margin-bottom:26px;
}

.card{
 width:420px;
 border:1px solid #222;
 border-radius:12px;
 padding:22px;
 margin-bottom:18px;
}

.dash-link{
 display:block;
 text-decoration:none;
 color:#fff;
 font-size:18px;
 padding:16px 0;
 position:relative;
}

.dash-link::after{
 content:"";
 position:absolute;
 left:50%;
 bottom:-6px;
 width:0;
 height:2px;
 background:#fff;
 transition:.3s;
 transform:translateX(-50%);
}

.dash-link:hover::after{
 width:60%;
}

footer{
 border-top:1px solid #222;
 padding:22px;
 text-align:center;
 font-size:13px;
 opacity:.7;
}
</style>
</head>

<body>

<div class="light-top"></div>
<div class="light-bottom"></div>

<!-- Common navbar -->
<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>

<section class="page">

 <h1>Platform Admin Panel</h1>

 <div class="card">
  <a class="dash-link" href="<%=request.getContextPath()%>/platform/organizations">
   View organizations
  </a>
 </div>

 <div class="card">
  <a class="dash-link" href="<%=request.getContextPath()%>/platform/apis">
   Manage API services
  </a>
 </div>

 <div class="card">
  <a class="dash-link" href="<%=request.getContextPath()%>/platform/plans">
   Manage plans
  </a>
 </div>

</section>

<footer>
 © 2026 ApiHub · API Management Platform
</footer>

</body>
</html>
