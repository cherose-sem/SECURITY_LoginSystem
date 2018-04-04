<%-- 
    Document   : register
    Created on : Apr 4, 2018, 6:15:49 PM
    Author     : Cherry Rose Semeña
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <title>Register Page</title>
    </head>
    <body>
        <div class="page-header">
            <h1>SECURITY LOGIN SYSTEM</h1>
        </div>
        <div class="well well-sm" align="center">
            <form class="navbar-form navbar-center" name = "regForm" action="MyServlet" method="POST">
                <div class="form-group">
                    <h3>New user</h3>
                    <div>Username:</div>
                    <input type="text" style="width:10em;" class="form-control" name="name" required>
                </div>
                <br>
                <br>
                <div class="form-group">
                    <div>Password:</div>
                    <input type="password" style="width:10em;" class="form-control" name="pwd" required><br>
                </div>
                <br>
                <br>

                <button type="submit" class="btn btn-primary btn-md">Register</button>
                <input type="hidden" name="do_this" value="register" />

            </form>
        </div>
    </body>
</html>
