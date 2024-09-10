// Alterar visibilidade de formulário a depende do tipo
document.getElementById('tipo_usuario').addEventListener('change', function() {
    var tipoUsuario = this.value;
    var camposAluno = document.getElementById('camposAluno');
    var camposProfessor = document.getElementById('camposProfessor');
    
    camposAluno.classList.add('hidden');
    camposProfessor.classList.add('hidden');

    if (tipoUsuario === 'aluno') {
        camposAluno.classList.remove('hidden');
    } else if (tipoUsuario === 'professor') {
        camposProfessor.classList.remove('hidden');
    }
});

// formatar o cpf 
function formatCPF(value) {
    // Remove todos os caracteres que não são dígitos
    value = value.replace(/\D/g, '');
    // Formata o CPF para o formato 000.000.000-00
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    return value;
}

document.getElementById('cpf').addEventListener('input', function() {
    this.value = formatCPF(this.value);
});

document.getElementById('cpf_prof').addEventListener('input', function() {
    this.value = formatCPF(this.value);
});

// Validar campos do formulário
document.getElementById('cadastro-form').addEventListener('submit', function(event) {
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

    // Validação do tipo de usuário
    var tipoUsuario = document.getElementById('tipo_usuario').value;
    if (!tipoUsuario) {
        document.getElementById('errorTipoUsuario').textContent = 'Por favor, selecione o tipo de usuário.';
        valid = false;
    }

    // Validação específica para Aluno
    if (tipoUsuario === 'aluno') {
        var matricula = document.getElementById('matricula').value;
        var cpf = document.getElementById('cpf').value;
        var curso = document.getElementById('curso').value;
	
		// Regex para aceitar apenas números
		let onlyNumbers = /^[0-9]+$/;
        if (!matricula) {
            document.getElementById('errorMatricula').textContent = 'Por favor, preencha a matrícula.';
            valid = false;
        } else if (!onlyNumbers.test(matricula)) {
	        document.getElementById('errorMatricula').textContent = 'A matrícula deve conter apenas números.';
	        valid = false;
	    } else {
	        document.getElementById('errorMatricula').textContent = ''; // Limpa o erro
	    }

        if (!cpf) {
            document.getElementById('errorCpf').textContent = 'Por favor, preencha o CPF.';
            valid = false;
        } else if (!/^\d{3}\.\d{3}\.\d{3}-\d{2}$/.test(cpf)) { // Exemplo básico de validação de CPF
            document.getElementById('errorCpf').textContent = 'Por favor, insira um CPF válido.';
            valid = false;
        }

        if (!curso) {
            document.getElementById('errorCurso').textContent = 'Por favor, preencha o curso.';
            valid = false;
        }
    }

    // Validação específica para Professor
    if (tipoUsuario === 'professor') {
        var matriculaProf = document.getElementById('matricula_prof').value;
        var cpfProf = document.getElementById('cpf_prof').value;

        if (!matriculaProf) {
            document.getElementById('errorMatriculaProf').textContent = 'Por favor, preencha a matrícula.';
            valid = false;
        }

        if (!cpfProf) {
            document.getElementById('errorCpfProf').textContent = 'Por favor, preencha o CPF.';
            valid = false;
        } else if (!/^\d{3}\.\d{3}\.\d{3}-\d{2}$/.test(cpfProf)) { // Exemplo básico de validação de CPF
            document.getElementById('errorCpfProf').textContent = 'Por favor, insira um CPF válido.';
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