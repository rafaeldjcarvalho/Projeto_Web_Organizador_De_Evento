<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String) request.getAttribute("criado");
	String erro = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
	<p><span class="sucess"><%if(msg != null) out.println(msg); %></span></p>
	<h1>Entrar</h1>
	<form id="login-form" method="POST" action="loginServlet">
		<p>
            <label for="email">E-mail:</label>
            <input type="email" name="email" id="email">
            <span class="error" id="errorEmail"></span>
        </p>
        <p>
            <label for="senha">Senha:</label>
            <input type="password" name="senha" id="senha">
            <span class="error" id="errorSenha"></span>
        </p>
        <input type="submit" value="Entrar">
        <p><span class="error"><%if(erro != null) out.println(erro); %></span></p>
	</form>
	<script src="scripts/login-form.js"></script>
</body>
</html>