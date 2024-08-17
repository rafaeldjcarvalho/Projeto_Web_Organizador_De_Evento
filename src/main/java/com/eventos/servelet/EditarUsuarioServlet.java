package com.eventos.servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.UsuarioDAO;
import com.eventos.model.Usuario;

@WebServlet("/editarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarUsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Processar os dados
		String nomeCompleto = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		// Pegar usuario
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		
		usuario.setNomeCompleto(nomeCompleto);
		usuario.setEmail(email);
		if(!senha.isEmpty()) usuario.setSenha(senha);
		
		// Guardar dados
		UsuarioDAO dao = new UsuarioDAO(ConnectionFactory.getConnection());
		try {
			dao.update(usuario);
		} catch(RuntimeException ex) {
			// caso de errado
			request.setAttribute("msg", "Erro ao alterar dados.");
			RequestDispatcher rd = request.getRequestDispatcher("conta_usuario.jsp");
			rd.forward(request, response);
		}
		// Caso de certo
		request.setAttribute("msg", "Alterações Salvas.");
		RequestDispatcher rd = request.getRequestDispatcher("conta_usuario.jsp");
		rd.forward(request, response);
	}

}
