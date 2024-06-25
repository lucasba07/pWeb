<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
    <title>Lista de Usuários</title>
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
            justify-content: center;
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-right: 10px;
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
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        td form {
            display: inline;
        }
        td button {
            padding: 5px 10px;
            margin: 2px 0;
            font-size: 14px;
        }
        
        .btn-voltar{
        	margin-top: 10px;     
        }
    </style>
</head>
<body>
    <div class="container">
		<h2>Lista de Usuários</h2>
        <form action="pesquisar" method="get">
            <input type="text" name="termo" placeholder="Pesquisar...">
            <button type="submit">Pesquisar</button>
        </form>
        <c:if test="${not empty usuarios}">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Senha</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nome}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.senha}</td>
                            <td>
                                <form action="atualizarUsuario.jsp" method="post">
                                    <input type="hidden" name="id" value="${usuario.id}">
                                    <button type="submit">Editar</button>
                                </form>
                                <form action="confirmarExclusao.jsp" method="get">
                                    <input type="hidden" name="id" value="${usuario.id}">
                                    <button type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <button class="btn-voltar" onclick="window.location.href='deslogar'">Deslogar</button>
        <button class="btn-voltar" onclick="window.location.href='listagemAtividades'">Atividades Realizadas</button>
    </div>
</body>
</html>
