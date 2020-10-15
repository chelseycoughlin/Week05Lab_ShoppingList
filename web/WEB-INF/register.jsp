<%-- 
    Document   : register
    Created on : Oct 15, 2020, 2:10:07 PM
    Author     : Chels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
        <form action="ShoppingList" method="POST">
            <label for="user">Username: </label><input type="text" name="user">
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register name">
            <br>
            ${message}
        </form>
    </body>
</html>
