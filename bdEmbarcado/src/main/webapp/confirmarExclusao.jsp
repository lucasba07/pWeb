<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<sql:setDataSource var="ds" driver="org.h2.Driver" url="jdbc:h2:~/test"
	user="sa" password="sa" />
	
<%
request.getSession(false);
if (session.getAttribute("email") == null) {
	request.setAttribute("erroCadastro", Boolean.TRUE);
    request.getRequestDispatcher("index.jsp").forward(request, response);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmar Exclusão</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 0;
}

.container {
	width: 90%;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	text-align: center;
}

h2 {
	margin-bottom: 20px;
}

button {
	padding: 10px 15px;
	background-color: #007bff;
	color: #fff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	margin: 5px;
}

button:hover {
	background-color: #0056b3;
}

.btn-cancelar {
	background-color: #6c757d;
}

.btn-cancelar:hover {
	background-color: #5a6268;
}
</style>
</head>
<body>
	<div class="container">
		<sql:query dataSource="${ds}" var="usuario"
			sql="SELECT * FROM usuario WHERE id = ?">
			<sql:param value="${param.id}" />
		</sql:query>
		<c:forEach var="u" items="${usuario.rows}">
			<h2>Tem certeza que deseja excluir o usuário "${u.nome}"?</h2>
			<form action="delete"
				method="post" style="display: inline;">
				<input type="hidden" name="id" value="${u.id}">
				<button type="submit">Confirmar</button>
			</form>

			<button class="btn-cancelar"
				onclick="window.location.href='listagemUsuario'">Cancelar</button>
		</c:forEach>
	</div>
</body>
</html>
