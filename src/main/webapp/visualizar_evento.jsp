<%@page import="com.eventos.model.Usuario"%>
<%@page import="com.eventos.model.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(usuario == null) response.sendRedirect("login.jsp");
	Evento evento = (Evento) request.getAttribute("evento");
	Usuario organizador = (Usuario) request.getAttribute("organizador");
	String erro = (String) request.getAttribute("erro");
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizar Evento</title>
    <link rel="stylesheet" href="css/style_navbar.css">
    <link rel="stylesheet" href="css/style_visualizar_evento.css">
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
    <h1><%out.println(evento.getTitulo());%></h1>
    <hr>
    <p>Descrição:</p>
    <p><%out.println(evento.getDescricao());%></p>
    <hr>
    <h3>Período de inscrição:</h3>
    <p>Data: De <%out.println(evento.getDataInicioDaInscricao().toLocalDate() + " às " + evento.getDataInicioDaInscricao().toLocalTime()
    		+ " até " + evento.getDataFinalDaInscricao().toLocalDate() + " às " + evento.getDataFinalDaInscricao().toLocalTime());%></p>
    <h3>Período de Duração do Evento:</h3>
    <p>Data: De <%out.println(evento.getDataInicialEvento().toLocalDate() + " às " + evento.getDataInicialEvento().toLocalTime()
    		+ " até " + evento.getDataFinalEvento().toLocalDate() + " às " + evento.getDataFinalEvento().toLocalTime());%></p>
    <hr>
    <p>Local: <%out.println(evento.getLocal()); %></p>
    <hr>
    <h3>Organizador</h3>
    <p><%out.println(organizador.getNomeCompleto()); %></p>
    <p><%out.println(organizador.getEmail()); %></p>
    <p><span class="sucess"><%if(msg != null) out.println(msg); %></span></p>
    <p><span class="error"><%if(erro != null) out.println(erro); %></span></p>
    <a class="button" href="inscricaoEventoServlet?acao=inserir&id_evento=<%out.println(evento.getId()); %>">Se increver no Evento</a>
    <%if((!usuario.getTipoUsuario().toString().equals("ALUNO") && usuario.getId() == organizador.getId()) || usuario.getTipoUsuario().toString().equals("ADMIN")){
    	out.println("<a class=\"button\" href=\"visualizarEventoServlet?acao=editar&id="+ evento.getId() +"\">Editar Evento</a>");
    	out.println("<a class=\"button\" href=\"visualizarEventoServlet?acao=deletar&id="+ evento.getId() +"\">Remover Evento</a>");
    	out.println("<a class=\"button\" href=\"visualizarEventoServlet?acao=listar&id="+ evento.getId() + "\">Listar Usuários Inscritos</a>");
    }%>
</body>
</html>