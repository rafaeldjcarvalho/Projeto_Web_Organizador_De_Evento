/**
 * 
 */
document.getElementById('login-form').addEventListener('submit', function(event) {
	var valid = true;
	
	// Limpar mensagens de erro
    document.querySelectorAll('.error').forEach(function(span) {
        span.textContent = '';
    });
	
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
    if (!senha) {
        document.getElementById('errorSenha').textContent = 'Por favor, preencha a senha.';
        valid = false;
    } else if (senha.length < 8) {
        document.getElementById('errorSenha').textContent = 'A senha deve ter pelo menos 8 caracteres.';
        valid = false;
    }

    // Impedir envio se o formulário não for válido
    if (!valid) {
        event.preventDefault();
    }
});