<%@page import="com.eventos.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
	if(usuario == null) response.sendRedirect("login.jsp");
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Minha Conta</title>
    <link rel="stylesheet" href="css/style_navbar.css">
    <link rel="stylesheet" href="css/style_conta.css">
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
	<h1>Detalhes da Conta</h1>
	<form id="editUsuario-form" method="POST" action="editarUsuarioServlet">
		<p>
            <label for="id_usuario">Id:</label>
            <input type="text" name="id_usuario" readonly="readonly" id="id_usuario" value="<%out.println(usuario.getId()); %>">
        </p>
        <p>
            <label for="nome">Nome Completo:</label>
            <input type="text" name="nome" id="nome" value="<%out.print(usuario.getNomeCompleto()); %>">
            <span class="error" id="errorNome"></span>
        </p>
        <%
        	if(usuario.getTipoUsuario().toString().equals("ALUNO")) {
        		out.println("<p>");
        			out.println("<label for=\"tipo_usuario\">Tipo de usuário:</label>");
        			out.println("<select id=\"tipo_usuario\" name=\"tipo_usuario\" required>");
        				out.println("<option value=\"aluno\">"+ usuario.getTipoUsuario().toString() +"</option>");
        			out.println("</select>");
        			out.println("<span class=\"error\" id=\"errorTipoUsuario\"></span>");
        		out.println("</p>");
        		out.println("<p>");
        			out.println("<label for=\"matricula\">Matrícula:</label>");
        			out.println("<input type=\"text\" id=\"matricula\" name=\"matricula\" readonly=\"readonly\" value=\""+ usuario.getMatricula() +"\">");
        			out.println("<span class=\"error\" id=\"errorMatricula\"></span>");
        		out.println("</p>");
        		out.println("<p>");
    				out.println("<label for=\"cpf\">CPF:</label>");
    				out.println("<input type=\"text\" id=\"cpf\" name=\"cpf\" readonly=\"readonly\" value=\""+ usuario.getCpf() +"\">");
    				out.println("<span class=\"error\" id=\"errorCpf\"></span>");
    			out.println("</p>");
    			out.println("<p>");
					out.println("<label for=\"curso\">Curso:</label>");
					out.println("<input type=\"text\" id=\"curso\" name=\"curso\" value=\""+ usuario.getCurso() +"\">");
					out.println("<span class=\"error\" id=\"errorCurso\"></span>");
				out.println("</p>");
        	} else {
        		out.println("<p>");
    				out.println("<label for=\"tipo_usuario\">Tipo de usuário:</label>");
    				out.println("<select id=\"tipo_usuario\" name=\"tipo_usuario\" required>");
    					out.println("<option value=\""+ usuario.getTipoUsuario().toString().toLowerCase() +"\">"+ usuario.getTipoUsuario().toString() +"</option>");
    				out.println("</select>");
    				out.println("<span class=\"error\" id=\"errorTipoUsuario\"></span>");
    			out.println("</p>");
    			out.println("<p>");
    				out.println("<label for=\"matricula_prof\">Matrícula:</label>");
    				out.println("<input type=\"text\" id=\"matricula_prof\" name=\"matricula_prof\" readonly=\"readonly\" value=\""+ usuario.getMatricula() +"\">");
    				out.println("<span class=\"error\" id=\"errorMatriculaProf\"></span>");
    			out.println("</p>");
    			out.println("<p>");
					out.println("<label for=\"cpf_prof\">CPF:</label>");
					out.println("<input type=\"text\" id=\"cpf_prof\" name=\"cpf_prof\" readonly=\"readonly\" value=\""+ usuario.getCpf() +"\">");
					out.println("<span class=\"error\" id=\"errorCpfProf\"></span>");
				out.println("</p>");
        	}
        %>
        <p>
            <label for="email">E-mail:</label>
            <input type="email" name="email" id="email" value="<%out.print(usuario.getEmail()); %>">
            <span class="error" id="errorEmail"></span>
        </p>
        <p>
            <label for="senha">Nova Senha:</label>
            <input type="password" name="senha" id="senha">
            <span class="error">(Deixe em branco para não alterar a senha.)</span>
            <span class="error" id="errorSenha"></span>
        </p>
        <input type="submit" value="Salvar Alterações">
        <p><span class="success"><%if(msg != null) out.println(msg); %></span></p>
    </form>
    <script src="scripts/edit-usu-form.js"></script>
</body>
</html>