<%-- 
    Document   : shoppinglist
    Created on : Oct 15, 2020, 2:10:14 PM
    Author     : Chels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1> Shopping List </h1>
        <p> Hello <c:out value="${user}"/>.</p>
        <p><a href="/shoppinglist?action=logout">Logout</a></p>
        
        <form action="" method="POST">
            <h2>Add Item</h2>
            <input type="text" name="item"><input type="submit" value="Add Item">
            <input type="hidden" name="action" value="add">
        </form>
        
        <form action="" method="POST">
        <ul>
            <c:forEach items="${items}" var="item">
                <li>
                    <label>
                        <input type="radio" name="item" value="<c:out value="${item}"/>">
                        <c:out value="${item}"/>
                    </label>
                </li>
            </c:forEach>
        </ul>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
