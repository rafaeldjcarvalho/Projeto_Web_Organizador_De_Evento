<%@page import="com.eventos.DAO.ConnectionFactory"%>
<%@page import="com.eventos.DAO.EventoDAO"%>
<%@page import="com.eventos.model.Evento"%>
<%@page import="java.util.List"%>
<%@page import="com.eventos.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(usuario == null) response.sendRedirect("login.jsp");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="css/style_navbar.css">
    <link rel="stylesheet" href="css/style_home.css">
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
	<h1>Eventos Publicados</h1>
	<div class="eventos-container">
		<%
			List<Evento> eventos = new EventoDAO(ConnectionFactory.getConnection()).findAll();
			if (eventos.isEmpty()) {
				out.println("<span>Não há eventos cadastrados.</span>");
			} else {
				for(Evento evento : eventos) {
					out.println("<div class=\"event-card\">");
						out.println("<h2><a class=\"titulo\" href=\"visualizarEventoServlet?acao=ver&id="+ evento.getId() +"\">"+ evento.getTitulo() +"</a></h2>");
						out.println("<p>Data de Inscrição: "+evento.getDataInicioDaInscricao() +" - "+ evento.getDataFinalDaInscricao() +"</p>");
						out.println("<p>Local: "+ evento.getLocal() +"</p>");
						out.println("<p>Total de Vagas: "+ evento.getVagasDeInscricao() +"</p>");
					out.println("</div>");
				}
			}
		%>
	</div>
</body>
</html>