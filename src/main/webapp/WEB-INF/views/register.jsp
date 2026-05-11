<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>ApiHub – Registration</title>

<style>
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif;}
body{background:#000;color:#fff;overflow-x:hidden;}

.light-top{position:fixed;top:-220px;right:-220px;width:520px;height:520px;
background:radial-gradient(circle,rgba(200,200,200,.18),transparent 65%);}
.light-bottom{position:fixed;bottom:-220px;left:-220px;width:520px;height:520px;
background:radial-gradient(circle,rgba(160,160,160,.18),transparent 65%);}

.navbar{padding:22px 60px;display:flex;justify-content:space-between;border-bottom:1px solid #222;}
.logo{font-size:26px;font-weight:700;letter-spacing:3px;}

.page{min-height:78vh;display:flex;align-items:center;justify-content:center;}
.card{width:520px;border:1px solid #222;border-radius:10px;padding:30px;}

input,button{
 width:100%;padding:12px;border-radius:6px;border:1px solid #333;
 background:transparent;color:#fff;margin-bottom:14px;
}
button{background:#fff;color:#000;font-weight:600;border:none;cursor:pointer;}

footer{border-top:1px solid #222;padding:22px;text-align:center;font-size:13px;opacity:.7;}
</style>
</head>

<body>

<div class="light-top"></div>
<div class="light-bottom"></div>

<header class="navbar">
 <div class="logo">APIHUB</div>
</header>

<section class="page">
 <div class="card">
  <h2>Register Organization</h2><br>

  <form method="post" action="<%=request.getContextPath()%>/register">

   <h4>Company</h4>
   <input name="orgName" placeholder="Company name" required>
   <input name="orgEmail" type="email" placeholder="Company email" required>

   <h4>Admin</h4>
   <input name="adminName" placeholder="Admin name" required>
   <input name="adminEmail" type="email" placeholder="Admin email" required>
   <input name="password" type="password" placeholder="Password" required>

   <button type="submit">Register</button>
  </form>
 </div>
</section>

<footer>© 2026 ApiHub</footer>
</body>
</html>
