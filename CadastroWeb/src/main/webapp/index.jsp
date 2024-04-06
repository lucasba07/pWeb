<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Cadastro</title>
</head>
<body>

<div class="container">
    <div class="header">
        <h2>Serviço de Mensageria</h2>
        <p>Formulário para envio de e-mails</p>
    </div>
    <form action="mensageria" method="post">
        <div class="form-group">
            <label for="email">E-mail:</label>
            <select id="email" name="email">
               <option selected value="" disabled>Selecione um e-mail:</option>
                <c:forEach items="${listaDeUsuarios}" var="user">
                     <option value="${user.email}" ${not empty emailValue && emailValue == user.email ? 'selected' : ''}>${user.email}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <input type="radio" id="form" name="cumprimento" value="formal">
            <label for="form">Cumprimento formal</label>
            <input type="radio" id="hora" name="cumprimento" value="horario">
            <label for="hora">Cumprimento conforme horário</label>
        </div>

        <div class="form-group">
            <input type="checkbox" id="autoEmail" name="autoEmail">
            <label for="autoEmail">Incluir aviso de "E-mail automático"</label>
        </div>

        <div class="form-group">
            <label for="title">Título:</label>
            <input type="text" id="title" name="title" value="${not empty tituloValue ? tituloValue : ''}">
        </div>

        <div class="form-group">
            <label for="text">Texto:</label>
            <textarea id="text" name="text">${not empty textoValue ? textoValue : ''}</textarea>
        </div>

        <div class="button-group">
            <button type="submit" class="submit-btn_e">Enviar</button>
            <button type="button" class="submit-btn_v" onclick="history.back()">Voltar</button>
        </div>
        <div style="color: #d9534f; font-weight: bold; margin-top: 15px;">
            <c:if test="${incompleto}">
               Preencha os dados obrigatórios ('E-mail', 'Título' e 'Texto') antes de efetuar o envio!
            </c:if>
        </div>
    </form>
</div>
</body>
</html>
