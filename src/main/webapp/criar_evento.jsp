<%@page import="com.eventos.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(usuario == null) response.sendRedirect("login.jsp");
	String erro = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Evento</title>
    <link rel="stylesheet" href="css/style_navbar.css">
    <link rel="stylesheet" href="css/style_criarevento.css">
</head>
<body>
	<nav class="navbar">
        <!-- Nome do site ou da seção -->
        <div class="brand">Eventos</div>
        
        <!-- Links de navegação -->
        <div class="nav-links">
            <a href="controllerServlet?acao=home">Início</a>
            <%if(usuario != null) {
            	if(!usuario.getTipoUsuario().toString().equals("ALUNO")) {
            		out.println("<a href=\"controllerServlet?acao=criar\">Criar Evento</a>");
            	}
            }
            %>
            <a href="controllerServlet?acao=meusEventos">Meus Eventos</a>
            <a href="controllerServlet?acao=minhaConta"><%if (usuario != null) out.println(usuario.getEmail());%></a>
            <a href="logoutServlet">Sair</a>
        </div>
    </nav>
    <h1>Criar Evento</h1>
    <form id="criar_evento-form" method="POST" action="criarEventoServlet">
    	<p>
    		<label for="titulo">Titulo:</label>
    		<input type="text" name="titulo" id="titulo">
    		<span class="error" id="errorTitulo"></span>
    	</p>
    	<p>
    		<label for="descricao">Descrição:</label>
    		<input type="text" name="descricao" id="descricao">
    		<span class="error" id="errorDescricao"></span>
    	</p>
    	<hr>
    	<h3>Período de Inscrição</h3>
    	<p>
    		<label for="dt_inscricao_ini">Início da inscrição:</label>
    		<input type="date" name="dt_inscricao_ini" id="dt_inscricao_ini">
    		<span class="error" id="errorDt_inscricao_ini"></span>
    	</p>
    	<p>
    		<label for="h_inscricao_ini">Horário do início da inscrição:</label>
    		<input type="time" name="h_inscricao_ini" id="h_inscricao_ini">
    		<span class="error" id="errorH_inscricao_ini"></span>
    	</p>
    	<p>
    		<label for="dt_inscricao_fim">Fim da inscrição:</label>
    		<input type="date" name="dt_inscricao_fim" id="dt_inscricao_fim">
    		<span class="error" id="errorDt_inscricao_ini"></span>
    	</p>
    	<p>
    		<label for="h_inscricao_fim">Horário do fim da inscrição:</label>
    		<input type="time" name="h_inscricao_fim" id="h_inscricao_fim">
    		<span class="error" id="errorH_inscricao_fim"></span>
    	</p>
    	<hr>
    	<h3>Duração do Evento</h3>
    	<p>
    		<label for="dt_inicio">Início do evento:</label>
    		<input type="date" name="dt_inicio" id="dt_inicio">
    		<span class="error" id="errorDt_inicio"></span>
    	</p>
    	<p>
    		<label for="h_inicio">Horário do início do evento:</label>
    		<input type="time" name="h_inicio" id="h_inicio">
    		<span class="error" id="errorH_inicio"></span>
    	</p>
    	<p>
    		<label for="dt_fim">Fim do evento:</label>
    		<input type="date" name="dt_fim" id="dt_fim">
    		<span class="error" id="errorDt_fim"></span>
    	</p>
    	<p>
    		<label for="h_fim">Horário do fim do evento:</label>
    		<input type="time" name="h_fim" id="h_fim">
    		<span class="error" id="errorH_fim"></span>
    	</p>
    	<hr>
    	<p>
    		<label for="local">Local do evento:</label>
    		<input type="text" name="local" id="local">
    		<span class="error" id="errorLocal"></span>
    	</p>
    	<p>
    		<label for="vagas">Quantidade de vagas do evento:</label>
    		<input type="number" name="vagas" id="vagas">
    		<span class="error" id="errorVagas"></span>
    	</p>
    	<input type="submit" value="Criar">
    	<p><span class="error"><%if(erro != null) out.println(erro); %></span></p>
    </form>
    <script src="scripts/evento-form.js"></script>
</body>
</html>