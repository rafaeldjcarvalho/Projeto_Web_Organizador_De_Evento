package com.eventos.servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.EventoDAO;
import com.eventos.model.Evento;
import com.eventos.model.Usuario;

@WebServlet("/criarEventoServlet")
public class CriarEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CriarEventoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		// Processar dados do formulário
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
		
		Evento evento = new Evento(titulo, descricao, LocalDateTime.of(LocalDate.parse(dt_inicio_inscricao), 
				LocalTime.parse(h_inicio_inscricao)), LocalDateTime.of(LocalDate.parse(dt_fim_inscricao), 
						LocalTime.parse(h_fim_inscricao)), LocalDateTime.of(LocalDate.parse(dt_inicio), 
								LocalTime.parse(h_inicio)), LocalDateTime.of(LocalDate.parse(dt_fim),
										LocalTime.parse(h_fim)), local, usuario.getId(), Integer.valueOf(vagas));
		
		EventoDAO dao = new EventoDAO(ConnectionFactory.getConnection());
		
		try {
			evento = dao.save(evento);
		} catch(RuntimeException ex) {
			// caso tenha algum erro
			request.setAttribute("erro", "Dados Inválidos!");
			RequestDispatcher rd = request.getRequestDispatcher("criar_evento.jsp");
			rd.forward(request, response);
			System.out.println(ex.getMessage());
		}
		
		// Se for salvo no banco
		RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
		
		
	}

}
