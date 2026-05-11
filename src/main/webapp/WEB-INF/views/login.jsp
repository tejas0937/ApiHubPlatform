<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>ApiHub – Login</title>

<style>
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif;}
body{background:#000;color:#fff;overflow-x:hidden;}

.light-top{position:fixed;top:-220px;right:-220px;width:520px;height:520px;
background:radial-gradient(circle,rgba(200,200,200,.18),transparent 65%);}
.light-bottom{position:fixed;bottom:-220px;left:-220px;width:520px;height:520px;
background:radial-gradient(circle,rgba(160,160,160,.18),transparent 65%);}

.navbar{
 padding:22px 60px;
 display:flex;
 justify-content:space-between;
 align-items:center;
 border-bottom:1px solid #222;
}
.logo{font-size:26px;font-weight:700;letter-spacing:3px;}
.nav-links{display:flex;gap:36px;}
.nav-links a{
 color:#fff;
 text-decoration:none;
 font-size:17px;
 position:relative;
}
.nav-links a::after{
 content:"";
 position:absolute;
 left:0;
 bottom:-6px;
 width:0;
 height:2px;
 background:#fff;
 transition:.3s;
}
.nav-links a:hover::after{width:100%;}

.page{min-height:78vh;display:flex;align-items:center;justify-content:center;}
.card{width:420px;border:1px solid #222;border-radius:10px;padding:30px;}

input,button{
 width:100%;
 padding:12px;
 border-radius:6px;
 border:1px solid #333;
 background:transparent;
 color:#fff;
 margin-bottom:16px;
}
button{
 background:#fff;
 color:#000;
 font-weight:600;
 border:none;
 cursor:pointer;
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

<header class="navbar">
 <div class="logo">APIHUB</div>
 <nav class="nav-links">
  <a href="<%=request.getContextPath()%>/index">Home</a>
  <a href="#">Login</a>
 </nav>
</header>

<section class="page">
 <div class="card">
  <h2>Login</h2><br>

  <form method="post" action="<%=request.getContextPath()%>/login">
   <input type="email" name="email" placeholder="Email" required>
   <input type="password" name="password" placeholder="Password" required>
   <button type="submit">Login</button>
  </form>
 </div>
</section>

<footer>
 © 2026 ApiHub · API Management Platform
</footer>

</body>
</html>
