package com.eventos.servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.EventoDAO;
import com.eventos.DAO.UsuarioDAO;
import com.eventos.model.Evento;
import com.eventos.model.Usuario;

@WebServlet("/visualizarEventoServlet")
public class VisualizarEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public VisualizarEventoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String idEvento = request.getParameter("id");
		int id = Integer.parseInt(idEvento);
		
		EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
		Evento evento = eventoDAO.findById(id).get();
		if(acao.equals("ver")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
			Usuario organizador = usuarioDAO.findById(evento.getOrganizador()).get();
			
			request.setAttribute("evento", evento);
			request.setAttribute("organizador", organizador);
			RequestDispatcher rd = request.getRequestDispatcher("visualizar_evento.jsp");
			rd.forward(request, response);
		} else if (acao.equals("editar")) {
			request.setAttribute("evento", evento);
			RequestDispatcher rd = request.getRequestDispatcher("editar_evento.jsp");
			rd.forward(request, response);
		} else if (acao.equals("deletar")) {
			eventoDAO.delete(id);
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else if (acao.equals("listar")) {
			request.setAttribute("evento", evento);
			request.setAttribute("qtdVaga", eventoDAO.qtdVaga(id));
			RequestDispatcher rd = request.getRequestDispatcher("visualizar_usuarios.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Processar dados do formulário
		String id_evento = request.getParameter("id_evento");
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String dt_inicio_inscricao = request.getParameter("dt_inscricao_ini");
		String h_inicio_inscricao = request.getParameter("h_inscricao_ini");
		String dt_fim_inscricao = request.getParameter("dt_inscricao_fim");
		String h_fim_inscricao = request.getParameter("h_inscricao_fim");
		String dt_inicio = request.getParameter("dt_inicio");
		String h_inicio = request.getParameter("h_inicio");
		String dt_fim = request.getParameter("dt_fim");
		String h_fim = request.getParameter("h_fim");
		String local = request.getParameter("local");
		String vagas = request.getParameter("vagas");
		
		// Pegar o evento no banco
		EventoDAO dao = new EventoDAO(ConnectionFactory.getConnection());
		Evento novoEvento = dao.findById(Integer.parseInt(id_evento)).get();
		
		// Fazer as alterações no evento
		novoEvento.setTitulo(titulo);
		novoEvento.setDescricao(descricao);
		novoEvento.setDataInicioDaInscricao(LocalDateTime.of(LocalDate.parse(dt_inicio_inscricao), 
				LocalTime.parse(h_inicio_inscricao)));
		novoEvento.setDataFinalDaInscricao(LocalDateTime.of(LocalDate.parse(dt_fim_inscricao), 
						LocalTime.parse(h_fim_inscricao)));
		novoEvento.setDataInicialEvento(LocalDateTime.of(LocalDate.parse(dt_inicio), 
								LocalTime.parse(h_inicio)));
		novoEvento.setDataFinalEvento(LocalDateTime.of(LocalDate.parse(dt_fim),
										LocalTime.parse(h_fim)));
		novoEvento.setLocal(local);
		novoEvento.setVagasDeInscricao(Integer.parseInt(vagas));
		
		// Salvar alterações no Banco
		try {
			dao.update(novoEvento);
		} catch(RuntimeException ex) {
			// caso tenha algum erro
			request.setAttribute("erro", "Dados Inválidos!");
			RequestDispatcher rd = request.getRequestDispatcher("editar_evento.jsp");
			rd.forward(request, response);
			System.out.println(ex.getMessage());
		}
		// Se for salvo no banco
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		
	}

}
