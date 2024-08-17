/**
 * 
 */
// Validar campos do formulário
document.getElementById('editUsuario-form').addEventListener('submit', function(event) {
    var valid = true;

    // Limpar mensagens de erro
    document.querySelectorAll('.error').forEach(function(span) {
        span.textContent = '';
    });

    // Validação do nome
    var nome = document.getElementById('nome').value;
    if (!nome) {
        document.getElementById('errorNome').textContent = 'Por favor, preencha o nome.';
        valid = false;
    }

    // Validação específica para Aluno
	var tipoUsuario = document.getElementById('tipo_usuario').value;
    if (tipoUsuario === 'aluno') {
        var curso = document.getElementById('curso').value;

        if (!curso) {
            document.getElementById('errorCurso').textContent = 'Por favor, preencha o curso.';
            valid = false;
        }
    }

    // Validação do email
    var email = document.getElementById('email').value;
    if (!email) {
        document.getElementById('errorEmail').textContent = 'Por favor, preencha o email.';
        valid = false;
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        document.getElementById('errorEmail').textContent = 'Por favor, insira um email válido.';
        valid = false;
    }

    // Validação da senha
    var senha = document.getElementById('senha').value;
    if (senha !== "" && senha.length < 8) {
        document.getElementById('errorSenha').textContent = 'A senha deve ter pelo menos 8 caracteres.';
        valid = false;
    }

    // Impedir envio se o formulário não for válido
    if (!valid) {
        event.preventDefault();
    }
});




