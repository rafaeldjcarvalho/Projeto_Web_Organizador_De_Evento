<%@page import="com.eventos.model.Inscrito"%>
<%@page import="com.eventos.DAO.ConnectionFactory"%>
<%@page import="com.eventos.DAO.UsuarioDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.eventos.model.Usuario"%>
<%@page import="com.eventos.model.Evento"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(usuario == null) response.sendRedirect("login.jsp");
	Evento evento = (Evento) request.getAttribute("evento");
	int quantidadeVaga = (int) request.getAttribute("qtdVaga");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizar Usuarios Inscritos</title>
    <link rel="stylesheet" href="css/style_navbar.css">
    <link rel="stylesheet" href="css/style_visualizar_usuarios.css">
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
	<h1>Usuários Inscritos</h1>
	<h2>Evento: <%out.println(evento.getTitulo()); %></h2>
	<p>Quantidade de inscritos: <%out.println(quantidadeVaga); %></p>
	<hr>
	<div class="usuarios-container">
	<%
		List<Inscrito> lista = new UsuarioDAO(ConnectionFactory.getConnection()).findAllUsersInEvent(evento.getId());
		if (lista.isEmpty()) {
			out.println("<span>Não há usuários inscritos.</span>");
		} else {
			for(Inscrito usu : lista) {
				out.println("<div class=\"usu-card\">");
					out.println("<div class=\"titulo\"><h3>"+ usu.getNomeCompleto() +"</h3></div>");
					out.println("<div class=\"details\">");
						out.println("<p><strong>Id: "+ usu.getId() +"</strong></p>");
						out.println("<p><strong>Matricula: "+ usu.getMatricula() +"</strong></p>");
						out.println("<p><strong>CPF: "+ usu.getCpf() +"</strong></p>");
						out.println("<p><strong>Tipo de Usuario: "+ usu.getTipoUsuario().toString() +"</strong></p>");
						out.println("<p><strong>Curso: "+ usu.getCurso() +"</strong></p>");
						out.println("<p><strong>Presença: "+ usu.getStatus() +"</strong></p>");
					out.println("</div>");
					out.println("<a class=\"btn\" href=\"inscricaoEventoServlet?acao=alterarStatus&id_usu="+ usu.getId() +"&id_evento="+ evento.getId() +"&status="+ usu.getStatus() +"\">Alterar Presença</a>");
				out.println("</div>");
			}
		}
	%>
	</div>
</body>
</html>