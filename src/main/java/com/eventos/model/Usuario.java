package com.eventos.model;

public class Usuario {
	private int id;
	private String nomeCompleto;
	private String matricula;
	private String cpf;
	private String curso;
	private String email;
	private String senha;
	private TipoUsuario tipoUsuario;
	
	public Usuario(String nomeCompleto, String matricula, String cpf, String curso, String email, 
			String senha, TipoUsuario tipoUsuario) {
		setNomeCompleto(nomeCompleto);
		setMatricula(matricula);
		setCpf(cpf);
		setCurso(curso);
		setEmail(email);
		setSenha(senha);
		setTipoUsuario(tipoUsuario);
	}
	
	public Usuario(int id, String nomeCompleto, String matricula, String cpf, String curso, String email, 
			String senha, TipoUsuario tipoUsuario) {
		this(nomeCompleto, matricula, cpf, curso, email, senha, tipoUsuario);
		setId(id);
	}
	
	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
