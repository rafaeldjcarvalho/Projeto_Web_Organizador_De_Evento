package com.eventos.servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String destino = null;
		//System.out.println(acao);
		if(acao.equals("home")) {
			destino = "home.jsp";
		} else if(acao.equals("criar")) {
			destino = "criar_evento.jsp";
		} else if(acao.equals("meusEventos")) {
			destino = "meus_eventos.jsp";
		} else if(acao.equals("minhaConta")) {
			destino = "conta_usuario.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
