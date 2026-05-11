<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ApiHub – Organization Admin</title>

    <style>
        /* Base Styles */
        * { margin:0; padding:0; box-sizing:border-box; font-family:"Segoe UI",Arial,sans-serif; }
        body { background:#000; color:#fff; overflow-x:hidden; }

        /* Background Glows */
        .light-top {
            position:fixed; top:-220px; right:-220px; width:520px; height:520px;
            background:radial-gradient(circle,rgba(200,200,200,.18),transparent 65%);
            pointer-events:none;
        }
        .light-bottom {
            position:fixed; bottom:-220px; left:-220px; width:520px; height:520px;
            background:radial-gradient(circle,rgba(160,160,160,.18),transparent 65%);
            pointer-events:none;
        }

        /* Layout */
        .page {
            min-height:78vh;
            display:flex;
            flex-direction:column;
            align-items:center;
            padding:60px 20px;
            text-align:center;
        }

        .page h1 { font-size:40px; margin-bottom:26px; }

        /* Navigation Cards */
        .card-container {
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
            justify-content: center;
            margin-bottom: 40px;
        }

        .card {
            width:300px;
            border:1px solid #222;
            border-radius:12px;
            padding:18px;
            transition: border 0.3s;
        }

        .card:hover { border-color: #444; }

        .dash-link {
            display:block;
            text-decoration:none;
            color:#fff;
            font-size:16px;
            position:relative;
            padding: 10px 0;
        }

        /* Table Section Styles */
        .log-container {
            width: 100%;
            max-width: 900px;
            background: rgba(10, 10, 10, 0.5);
            border: 1px solid #222;
            border-radius: 12px;
            padding: 30px;
            margin-top: 20px;
        }

        .log-container h2 {
            font-size: 22px;
            margin-bottom: 20px;
            text-align: left;
            border-left: 3px solid #fff;
            padding-left: 15px;
        }

        .usage-table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }

        .usage-table th {
            border-bottom: 2px solid #222;
            padding: 12px 15px;
            font-size: 13px;
            color: #666;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .usage-table td {
            padding: 15px;
            border-bottom: 1px solid #111;
            font-size: 14px;
            color: #ccc;
        }

        .usage-table tr:last-child td { border-bottom: none; }
        
        .usage-table tr:hover { background: rgba(255, 255, 255, 0.02); }

        code {
            background: #1a1a1a;
            padding: 3px 6px;
            border-radius: 4px;
            font-family: "Consolas", monospace;
            color: #aaa;
        }

        .usage-count {
            font-weight: bold;
            color: #fff;
        }

        footer {
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

<jsp:include page="/WEB-INF/views/common/navbar.jsp"/>

<section class="page">

    <h1>Organization Admin Panel</h1>

    <div class="card-container">
        <div class="card">
            <a class="dash-link" href="<%=request.getContextPath()%>/org/add-employee">
                View Employees
            </a>
        </div>
        <div class="card">
            <a class="dash-link" href="<%=request.getContextPath()%>/org/api-keys">
                Manage API keys
            </a>
        </div>
        <div class="card">
            <a class="dash-link" href="<%=request.getContextPath()%>/org/plans">
                Subscribe to plan
            </a>
        </div>
    </div>

    <div class="log-container">
        <h2>View Usage Logs</h2>
        <table class="usage-table">
            <thead>
                <tr>
                    <th>API Key</th>
                    <th>Service ID</th>
                    <th>Subscription ID</th>
                    <th>Usage (Hits)</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><code>168bc93e-5235-4a2c-bbca-23b3cf2ae82c</code></td>
                    <td>MyApi</td>
                    <td>101</td>
                    <td class="usage-count">137</td>
                </tr>
                
            </tbody>
        </table>
    </div>

</section>

<footer>
    © 2026 ApiHub · API Management Platform
</footer>

</body>
</html>