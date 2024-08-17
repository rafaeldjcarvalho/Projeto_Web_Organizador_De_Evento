package com.eventos.servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.EventoDAO;
import com.eventos.DAO.UsuarioDAO;
import com.eventos.model.Evento;
import com.eventos.model.Usuario;

@WebServlet("/inscricaoEventoServlet")
public class InscricaoEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public InscricaoEventoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if(acao.equals("inserir")) {
			String id_evento = request.getParameter("id_evento");
			
			// Pegar o id do usuário logado
			HttpSession session = request.getSession();
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			// Adicionar usuário ao evento
			UsuarioDAO dao = new UsuarioDAO(ConnectionFactory.getConnection());
			
			EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
			Evento evento = eventoDAO.findById(Integer.parseInt(id_evento)).get();
			Usuario organizador = dao.findById(evento.getOrganizador()).get();
			request.setAttribute("evento", evento);
			request.setAttribute("organizador", organizador);
			
			boolean podeInscrever = false;
			LocalDateTime agora = LocalDateTime.now();
			if((evento.getDataFinalDaInscricao().compareTo(agora) >= 0) &&
					evento.getDataInicioDaInscricao().compareTo(agora) <= 0) {
				if(eventoDAO.qtdVaga(Integer.parseInt(id_evento)) < evento.getVagasDeInscricao()) {
					podeInscrever = true;
				}
			}
			
			if(!podeInscrever) {
				request.setAttribute("erro", "Inscrição Não Pode ser Realizada!");
				RequestDispatcher rd = request.getRequestDispatcher("visualizar_evento.jsp");
				rd.forward(request, response);
			} else {
				try { 
					dao.saveUserInEvent(Integer.parseInt(id_evento), usuario.getId()); 
				} catch(RuntimeException ex) { 
					// Caso de algum erro
					request.setAttribute("erro", "Falha ao inscrever/Já inscrito!");
					RequestDispatcher rd = request.getRequestDispatcher("visualizar_evento.jsp");
					rd.forward(request, response); System.out.println(ex.getMessage()); 
				} 
				// Caso de certo 
				request.setAttribute("msg", "Inscrição Realizada!");
				RequestDispatcher rd = request.getRequestDispatcher("visualizar_evento.jsp");
				rd.forward(request, response);
			}
		} else if (acao.equals("deletar")) {
			String id_part = request.getParameter("id_part");
			UsuarioDAO dao = new UsuarioDAO(ConnectionFactory.getConnection());
			
			dao.deleteUserInEvent(Integer.parseInt(id_part));
			
			RequestDispatcher rd = request.getRequestDispatcher("meus_eventos.jsp");
			rd.forward(request, response);
		} else if (acao.equals("alterarStatus")) {
			String id_usu = request.getParameter("id_usu");
			String id_evento = request.getParameter("id_evento");
			String status = request.getParameter("status");
			
			if(status.equals("AUSENTE")) {
				status = "PRESENTE";
			} else {
				status = "AUSENTE";
			}
			
			UsuarioDAO dao = new UsuarioDAO(ConnectionFactory.getConnection());
			dao.updateUserInEvent(Integer.parseInt(id_evento), Integer.parseInt(id_usu), status);
			
			EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
			Evento evento = eventoDAO.findById(Integer.parseInt(id_evento)).get();
			int qtdVaga = eventoDAO.qtdVaga(Integer.parseInt(id_evento));
			
			request.setAttribute("evento", evento);
			request.setAttribute("qtdVaga", qtdVaga);
			RequestDispatcher rd = request.getRequestDispatcher("visualizar_usuarios.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
