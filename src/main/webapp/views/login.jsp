<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 06/08/2021
  Time: 09:29 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${context}/assets/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${context}/assets/dist/css/styles.css">
</head>
<body>
<h1>Login</h1>
<form action="${context}/login" method="POST">
    <input type="hidden" value="loginUser" name="action">
    <label>Correo:</label>
    <input class="form-control" type="email" name="email" />
    <br>
    <label>Contraseña:</label>
    <input class="form-control" type="password" name="password" />

    <button type="submit" class="btn btn-success"> Log In </button>
</form>
<script src="${context}/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>
