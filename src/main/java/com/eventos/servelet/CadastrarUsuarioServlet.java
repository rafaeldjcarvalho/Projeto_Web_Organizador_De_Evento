package com.eventos.servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.UsuarioDAO;
import com.eventos.model.TipoUsuario;
import com.eventos.model.Usuario;

@WebServlet("/cadastrarUsuarioServlet")
public class CadastrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CadastrarUsuarioServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("cadastro_usuario.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Processando dados
		String nomeCompleto = request.getParameter("nome");
		String tipoUsuario = request.getParameter("tipo_usuario");
		String matricula, cpf;
		String curso = null;
		if(tipoUsuario.equals("professor")) {
			matricula = request.getParameter("matricula_prof");
			cpf = request.getParameter("cpf_prof");
		} else {
			matricula = request.getParameter("matricula");
			cpf = request.getParameter("cpf");
			curso = request.getParameter("curso");
		}
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		// Criar Usuario
		Usuario usuario = new Usuario(nomeCompleto, matricula, cpf, curso, email, senha, TipoUsuario.valueOf(tipoUsuario.toUpperCase()));
		
		// Salvar no banco
		UsuarioDAO dao = new UsuarioDAO(ConnectionFactory.getConnection());
		try {
			usuario = dao.save(usuario);
		} catch(RuntimeException ex) { 
			// Caso tenha algum erro
			request.setAttribute("erro", "Dados Inválidos!");
			RequestDispatcher rd = request.getRequestDispatcher("cadastro_usuario.jsp");
			rd.forward(request, response);
			System.out.println(ex.getMessage());
		}
		
		// Salvo com sucesso, redirecionar ao login
		request.setAttribute("criado", "Usuário Cadastrado!");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

}
