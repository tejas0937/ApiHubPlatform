<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>ApiHub – Home</title>

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

.navbar{
 padding:22px 60px;display:flex;justify-content:space-between;align-items:center;
 border-bottom:1px solid #222;
}
.logo{font-size:26px;font-weight:700;letter-spacing:3px;}
.nav-links{display:flex;gap:36px;}
.nav-links a{
 color:#fff;text-decoration:none;font-size:17px;position:relative;
}
.nav-links a::after{
 content:"";position:absolute;left:0;bottom:-6px;width:0;height:2px;
 background:#fff;transition:.3s;
}
.nav-links a:hover::after{width:100%;}

.hero{
 min-height:78vh;display:flex;flex-direction:column;align-items:center;
 justify-content:center;text-align:center;padding:60px 20px;
}
.hero h1{font-size:52px;margin-bottom:18px;}
.hero p{max-width:760px;font-size:17px;line-height:1.8;opacity:.85;}
.hero-logo{margin-top:34px;font-size:70px;font-weight:800;letter-spacing:8px;}
.register-box{margin-top:36px;font-size:20px;}
.register-box a{color:#fff;font-weight:700;text-decoration:none;border-bottom:2px solid #fff;}

footer{
 border-top:1px solid #222;padding:22px;text-align:center;
 font-size:13px;opacity:.7;
}
</style>
</head>
<body>

<div class="light-top"></div>
<div class="light-bottom"></div>

<header class="navbar">
 <div class="logo">APIHUB</div>
 <nav class="nav-links">
  <a href="<%=request.getContextPath()%>/">Home</a>
  <a href="#">About</a>
  
  <a href="<%=request.getContextPath()%>/login-page">Login</a>
 </nav>
</header>

<section class="hero">
 <h1>Build, Scale and Monetize APIs with ApiHub</h1>
 <p>
  ApiHub is a unified API platform for organizations to access AI services,
  manage subscriptions, monitor usage and handle billing securely.
 </p>

 <div class="hero-logo">APIHUB</div>

 <div class="register-box">
  New here? <a href="<%=request.getContextPath()%>/register-page">Register !</a>
 </div>
</section>

<footer>
 © 2026 ApiHub · API Management Platform
</footer>

</body>
</html>
