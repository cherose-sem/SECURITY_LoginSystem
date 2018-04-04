<%-- 
    Document   : login
    Created on : Apr 4, 2018, 6:24:15 PM
    Author     : Cherry Rose Semeña
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <title>Login Page</title>
    </head>
    <body>
        <div class="page-header">
            <h1>SECURITY LOGIN SYSTEM</h1>
        </div>
        <div class="well well-sm" align="center">
            <form class="navbar-form navbar-center" name = "logInForm" action="MyServlet" method="POST">
                <div class="form-group">
                    <div>Username:</div>
                    <input type="text" style="width:10em;" class="form-control" name="name" value="${fn:escapeXml(name)}" required>
                </div>
                <br>
                <br>
                <div class="form-group">
                    <div>Password:</div>
                    <input type="password" style="width:10em;" class="form-control" name="pwd" required><br>
                </div>
                <br>
                <br>

                <button type="submit" class="btn btn-primary btn-md">Login</button>
                <input type="hidden" name="do_this" value="logIn" />

            </form>
        </div>
    </body>
</html>
