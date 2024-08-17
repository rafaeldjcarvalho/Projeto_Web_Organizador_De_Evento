/**
 * 
 */
// Validar campos do formulário
document.getElementById('criar_evento-form').addEventListener('submit', function(event) {
	event.preventDefault(); // Impede o envio do formulário inicialmente

    // Captura dos elementos do formulário
    const titulo = document.getElementById('titulo');
    const descricao = document.getElementById('descricao');
    const dtInscricaoIni = document.getElementById('dt_inscricao_ini');
    const hInscricaoIni = document.getElementById('h_inscricao_ini');
    const dtInscricaoFim = document.getElementById('dt_inscricao_fim');
    const hInscricaoFim = document.getElementById('h_inscricao_fim');
    const dtInicio = document.getElementById('dt_inicio');
    const hInicio = document.getElementById('h_inicio');
    const dtFim = document.getElementById('dt_fim');
    const hFim = document.getElementById('h_fim');
    const local = document.getElementById('local');
    const vagas = document.getElementById('vagas');

    // Limpeza de mensagens de erro anteriores
    document.querySelectorAll('.error').forEach(el => el.innerText = '');

    let isValid = true; // Estado de validade do formulário

    // Função auxiliar para mostrar mensagens de erro
    function showError(element, message) {
        const errorElement = document.getElementById('error' + element.id.charAt(0).toUpperCase() + element.id.slice(1));
        errorElement.innerText = message;
        isValid = false;
    }

    // Validação dos campos de texto
    if (!titulo.value.trim()) showError(titulo, 'O título é obrigatório.');
    if (!descricao.value.trim()) showError(descricao, 'A descrição é obrigatória.');
    if (!local.value.trim()) showError(local, 'O local é obrigatório.');

    // Validação dos campos numéricos
    if (!vagas.value.trim() || vagas.value <= 0) showError(vagas, 'A quantidade de vagas deve ser maior que zero.');

    // Validação das datas e horários
    const inscricaoInicio = new Date(`${dtInscricaoIni.value}T${hInscricaoIni.value}`);
    const inscricaoFim = new Date(`${dtInscricaoFim.value}T${hInscricaoFim.value}`);
    const eventoInicio = new Date(`${dtInicio.value}T${hInicio.value}`);
    const eventoFim = new Date(`${dtFim.value}T${hFim.value}`);

    if (isNaN(inscricaoInicio.getTime())) showError(dtInscricaoIni, 'Data de início da inscrição inválida.');
    if (isNaN(inscricaoFim.getTime())) showError(dtInscricaoFim, 'Data de fim da inscrição inválida.');
    if (isNaN(eventoInicio.getTime())) showError(dtInicio, 'Data de início do evento inválida.');
    if (isNaN(eventoFim.getTime())) showError(dtFim, 'Data de fim do evento inválida.');

    if (inscricaoInicio > inscricaoFim) showError(dtInscricaoFim, 'O fim da inscrição deve ser após o início.');
    if (eventoInicio > eventoFim) showError(dtFim, 'O fim do evento deve ser após o início.');
    if (eventoInicio < inscricaoFim) showError(dtInicio, 'O início do evento deve ser após o fim da inscrição.');

    // Se o formulário for válido, pode ser enviado
    if (isValid) {
        document.getElementById('criar_evento-form').submit();
    }
});