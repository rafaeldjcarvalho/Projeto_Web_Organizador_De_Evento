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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Processar login
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		UsuarioDAO dao = new UsuarioDAO(ConnectionFactory.getConnection());
		Usuario usuario = null; 
		try {
			usuario = dao.findByEmail(email).get();
		} catch(RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
		boolean loginAcesso = false;
		
		if(usuario != null && usuario.getSenha().equals(senha)) {
			loginAcesso = true;
		}
		
		if(loginAcesso) {
			// Criar sessão e criar atributo para salvar usuario
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("erro", "E-mail ou Senha Inválidos!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

}
