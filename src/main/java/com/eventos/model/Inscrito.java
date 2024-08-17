package com.eventos.model;

public class Inscrito extends Usuario{
	private String status;

	public Inscrito(int id, String nomeCompleto, String matricula, String cpf, String curso, String email, String senha,
			TipoUsuario tipoUsuario, String status) {
		super(id, nomeCompleto, matricula, cpf, curso, email, senha, tipoUsuario);
		setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
