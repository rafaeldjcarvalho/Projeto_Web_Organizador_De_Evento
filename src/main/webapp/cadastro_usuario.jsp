<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String) request.getAttribute("erro");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Usuário</title>
    <link rel="stylesheet" href="css/style_cadastro.css">
</head>
<body>
    <h1>Cadastrar Novo Usuário</h1>
    <form id="cadastro-form" method="POST" action="cadastrarUsuarioServlet">
        <p>
            <label for="nome">Nome Completo:</label>
            <input type="text" name="nome" id="nome">
            <span class="error" id="errorNome"></span>
        </p>
        <p>
            <label for="tipo_usuario">Tipo de usuário:</label>
            <select id="tipo_usuario" name="tipo_usuario" required>
                <option value="">- Selecione -</option>
                <option value="aluno">Aluno</option>
                <option value="professor">Professor</option>
            </select>
            <span class="error" id="errorTipoUsuario"></span>
        </p>
        <div id="camposAluno" class="hidden">
            <p>
                <label for="matricula">Matrícula:</label>
                <input type="text" id="matricula" name="matricula">
                <span class="error" id="errorMatricula"></span>
            </p>
            <p>
                <label for="cpf">CPF:</label>
                <input type="text" id="cpf" name="cpf">
                <span class="error" id="errorCpf"></span>
            </p>
            <p>
                <label for="curso">Curso:</label>
                <input type="text" id="curso" name="curso">
                <span class="error" id="errorCurso"></span>
            </p>
        </div>
    
        <div id="camposProfessor" class="hidden">
            <p>
                <label for="matricula_prof">Matrícula:</label>
                <input type="text" id="matricula_prof" name="matricula_prof">
                <span class="error" id="errorMatriculaProf"></span>
            </p>
            <p>
                <label for="cpf_prof">CPF:</label>
                <input type="text" id="cpf_prof" name="cpf_prof">
                <span class="error" id="errorCpfProf"></span>
            </p>
        </div>
       
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
        <input type="submit" value="Cadastrar">
        <p><span class="error"><%if(msg != null) out.println(msg); %></span></p>
    </form>
    <script src="scripts/cadastro-usu-form.js"></script>
</body>
</html>