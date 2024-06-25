<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sql:setDataSource var="ds" driver="org.h2.Driver"
    url="jdbc:h2:~/test" user="sa" password="sa" />
    
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
    <title>Atualizar Usuário</title>
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
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        input[type="text"], input[type="password"], input[type="email"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 10px;
            width: 300px;
        }
        button {
            padding: 10px 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Atualizar Usuário</h2>
        <sql:query dataSource="${ds}" var="usuario" sql="SELECT * FROM usuario WHERE id = ?">
            <sql:param value="${param.id}" />
        </sql:query>
        <c:forEach var="u" items="${usuario.rows}">
            <form action="atualizar" method="post">
                <input type="hidden" name="id" value="${u.id}">
                <input type="text" name="nome" value="${u.nome}" placeholder="Nome">
                <input type="email" name="email" value="${u.email}" placeholder="Email">
                <input type="password" name="senha" value="${u.senha}" placeholder="Senha">
                <button type="submit">Atualizar</button>
            </form>
            
            <c:if test="${mostrarAlertaErro}">
            	<div>
                	<p style="color: red; padding: 10px">Digite todos os dados para confirmar a atualização!</p>
            	</div>
        	</c:if>
        </c:forEach>
        <button class="btn-voltar" onclick="window.location.href='listagemUsuario'">Voltar</button>
    </div>
</body>
</html>
