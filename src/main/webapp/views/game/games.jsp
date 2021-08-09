<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 06/08/2021
  Time: 08:37 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="context" value ="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <title>Games</title>
</head>
<body>
<div id="content">
    <table class="table">
        <thead class="table-dark">
        <tr>
            <th>No.</th>
            <th>Nombre completo</th>
            <th>Correo</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ listUsers }" var="user" varStatus="status">
            <tr>
                <td>${ status.count }</td>
                <td>${ user.idPerson.name } ${ user.idPerson.lastname }</td>
                <td>${ user.email }</td>
                <td>
                    <c:if test="${ user.status == 1 }">
                        <span class="badge rounded-pill bg-success">Activo</span>
                    </c:if>
                    <c:if test="${ user.status == 0 }">
                        <span class="badge rounded-pill bg-danger">Inactivo</span>
                    </c:if>
                </td>
                <td>
                    <c:if test="${ user.status == 1 }">
                        <form action="${context}/getUserById" method="POST" style="display: inline;">
                            <input type="hidden" name="action" value="getUserById">
                            <input type="hidden" name="id" value="${ user.id }">
                            <button type="submit" class="btn btn-outline-primary"><i class="fas fa-edit"></i> Modificar</button>
                        </form>
                        <button id="btn-delete-${ status.count }" data-code="${ user.id }" data-text="${ user.idPerson.name } ${ user.idPerson.lastname }" type="button" class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#delete"><i class="fas fa-trash"></i> Eliminar</button>
                    </c:if>
                    <c:if test="${ user.status == 0 }">
                        <button id="btn-details-${ status.count }" data-code="${ user.id }" type="button" class="btn btn-outline-info" data-bs-toggle="modal" data-bs-target="#details"><i class="fas fa-info-circle"></i> Detalles</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${context}/assets/dist/js/main.js"></script>
</body>
</html>
