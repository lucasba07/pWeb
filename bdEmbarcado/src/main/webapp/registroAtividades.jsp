<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <title>Atividades Realizadas</title>
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
        button {
            padding: 10px 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .btn-voltar {
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Atividades Realizadas</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID_Usuario</th>
                    <th>Ação</th>
                    <th>Data_Hora</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${atividades}" var="ativ">
                    <tr>
                        <td>${ativ.id}</td>
                        <td>${ativ.usuario_id}</td>
                        <td>${ativ.acao}</td>
                        <td>${ativ.data_hora_formatada}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <button class="btn-voltar" onclick="window.location.href='listagemUsuario'">Voltar</button>
    </div>
</body>
</html>
