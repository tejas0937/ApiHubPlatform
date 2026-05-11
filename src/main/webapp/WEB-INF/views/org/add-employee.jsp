<%@ page import="java.util.List" %>
<%@ page import="com.apihub.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<title>Employees</title>

<style>
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial;}
body{background:#000;color:#fff;}

.navbar{padding:22px 60px;display:flex;justify-content:space-between;border-bottom:1px solid #222;}
.logo{font-size:26px;font-weight:700;}

.container{padding:40px 60px;}

.table-box{
 margin-bottom:40px;
 border:1px solid #222;
 border-radius:10px;
 padding:20px;
}

table{
 width:100%;
 border-collapse:collapse;
}

th,td{
 border-bottom:1px solid #222;
 padding:12px;
 text-align:left;
}

th{
 background:#111;
}

.card{
 border:1px solid #222;
 padding:30px;
 border-radius:10px;
 width:420px;
}

input,select,button{
 width:100%;
 padding:12px;
 border-radius:6px;
 border:1px solid #333;
 background:transparent;
 color:#fff;
 margin-bottom:14px;
}

button{
 background:#fff;
 color:#000;
 border:none;
 font-weight:600;
 cursor:pointer;
}

select option{color:black;}
</style>
</head>

<body>

<header class="navbar">
 <div class="logo">APIHUB</div>
</header>

<div class="container">


 <div class="table-box">

  <h3>Organization Employees</h3><br>

  <table>
   <tr>
    <th>Name</th>
    <th>Email</th>
    <th>Status</th>
   </tr>

<%
   List<User> employees =
       (List<User>) request.getAttribute("employees");

   if (employees != null && !employees.isEmpty()) {
       for (User u : employees) {
%>
   <tr>
    <td><%= u.getFullName() %></td>
    <td><%= u.getEmail() %></td>
    <td><%= u.getStatus() %></td>
   </tr>
<%
       }
   } else {
%>
   <tr>
    <td colspan="3">No employees found</td>
   </tr>
<%
   }
%>

  </table>

 </div>

 <!-- ================= ADD EMPLOYEE FORM ================= -->

 <div class="card">

  <h3>Add New Employee</h3><br>

  <form method="post"
        action="<%=request.getContextPath()%>/org/add-employee">

   <input name="name" placeholder="Full name" required>

   <input type="email"
          name="email"
          placeholder="Email"
          required>

   <input type="password"
          name="password"
          placeholder="Password"
          required>

   <select name="role" required>
    <option value="">Select role</option>
    <option value="VIEWER">Viewer</option>
    <option value="FINANCE">Finance</option>
   </select>

   <button type="submit">Create employee</button>

  </form>

 </div>

</div>

</body>
</html>
