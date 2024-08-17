<%@page import="com.eventos.model.Usuario"%>
<%@page import="com.eventos.model.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(usuario == null) response.sendRedirect("login.jsp");
	Evento evento = (Evento) request.getAttribute("evento");
	String erro = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Evento</title>
    <link rel="stylesheet" href="css/style_editarevento.css">
</head>
<body>
	<h1>Editar Evento</h1>
	<form id="criar_evento-form" method="POST" action="visualizarEventoServlet">
		<p>
    		<label for="id_evento">ID:</label>
    		<input type="text" name="id_evento" id="id_evento" readonly="readonly" value="<%out.println(evento.getId()); %>">
    		<span class="error" id="errorTitulo"></span>
    	</p>
    	<p>
    		<label for="titulo">Titulo:</label>
    		<input type="text" name="titulo" id="titulo" value="<%out.println(evento.getTitulo()); %>">
    		<span class="error" id="errorTitulo"></span>
    	</p>
    	<p>
    		<label for="descricao">Descrição:</label>
    		<input type="text" name="descricao" id="descricao" value="<%out.println(evento.getDescricao()); %>">
    		<span class="error" id="errorDescricao"></span>
    	</p>
    	<hr>
    	<h3>Período de Inscrição</h3>
    	<p>
    		<label for="dt_inscricao_ini">Início da inscrição:</label>
    		<input type="date" name="dt_inscricao_ini" id="dt_inscricao_ini" value="<%out.print(evento.getDataInicioDaInscricao().toLocalDate()); %>">
    		<span class="error" id="errorDt_inscricao_ini"></span>
    	</p>
    	<p>
    		<label for="h_inscricao_ini">Horário do início da inscrição:</label>
    		<input type="time" name="h_inscricao_ini" id="h_inscricao_ini" value="<%out.print(evento.getDataInicioDaInscricao().toLocalTime()); %>">
    		<span class="error" id="errorH_inscricao_ini"></span>
    	</p>
    	<p>
    		<label for="dt_inscricao_fim">Fim da inscrição:</label>
    		<input type="date" name="dt_inscricao_fim" id="dt_inscricao_fim" value="<%out.print(evento.getDataFinalDaInscricao().toLocalDate()); %>">
    		<span class="error" id="errorDt_inscricao_ini"></span>
    	</p>
    	<p>
    		<label for="h_inscricao_fim">Horário do fim da inscrição:</label>
    		<input type="time" name="h_inscricao_fim" id="h_inscricao_fim" value="<%out.print(evento.getDataFinalDaInscricao().toLocalTime()); %>">
    		<span class="error" id="errorH_inscricao_fim"></span>
    	</p>
    	<hr>
    	<h3>Duração do Evento</h3>
    	<p>
    		<label for="dt_inicio">Início do evento:</label>
    		<input type="date" name="dt_inicio" id="dt_inicio" value="<%out.print(evento.getDataInicialEvento().toLocalDate()); %>">
    		<span class="error" id="errorDt_inicio"></span>
    	</p>
    	<p>
    		<label for="h_inicio">Horário do início do evento:</label>
    		<input type="time" name="h_inicio" id="h_inicio" value="<%out.print(evento.getDataInicialEvento().toLocalTime()); %>">
    		<span class="error" id="errorH_inicio"></span>
    	</p>
    	<p>
    		<label for="dt_fim">Fim do evento:</label>
    		<input type="date" name="dt_fim" id="dt_fim" value="<%out.print(evento.getDataFinalEvento().toLocalDate()); %>">
    		<span class="error" id="errorDt_fim"></span>
    	</p>
    	<p>
    		<label for="h_fim">Horário do fim do evento:</label>
    		<input type="time" name="h_fim" id="h_fim" value="<%out.print(evento.getDataFinalEvento().toLocalTime()); %>">
    		<span class="error" id="errorH_fim"></span>
    	</p>
    	<hr>
    	<p>
    		<label for="local">Local do evento:</label>
    		<input type="text" name="local" id="local" value="<%out.println(evento.getLocal()); %>">
    		<span class="error" id="errorLocal"></span>
    	</p>
    	<p>
    		<label for="vagas">Quantidade de vagas do evento:</label>
    		<input type="number" name="vagas" id="vagas" value="<%out.print(evento.getVagasDeInscricao()); %>">
    		<span class="error" id="errorVagas"></span>
    	</p>
    	<input type="submit" value="Salvar Alterações">
    	<a class="botao" href="visualizarEventoServlet?acao=ver&id=<%out.print(evento.getId()); %>">Cancelar</a>
    	<p><span class="error"><%if(erro != null) out.println(erro); %></span></p>
    </form>
    <script src="scripts/evento-form.js"></script>
</body>
</html>