<%-- 
    Document   : welcome
    Created on : Apr 4, 2018, 4:13:05 PM
    Author     : Cherry Rose Semeña
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <title>Success Page</title>
    </head>
    <body>
        <div class="page-header">
            <h1>SECURITY LOGIN SYSTEM</h1>
        </div>

        <div class="alert alert-info" role="alert">
            <h3><strong><%= (String) session.getAttribute("msg")%></strong><br><br>
            You have successfully logged in.</h3>
        </div>
    </body>
</html>
