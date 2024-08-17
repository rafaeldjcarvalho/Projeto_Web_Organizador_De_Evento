package com.eventos.model;

import java.time.LocalDateTime;

public class Evento {
	private int id;
	private String titulo;
	private String descricao;
	private LocalDateTime dataInicioDaInscricao;
	private LocalDateTime dataFinalDaInscricao;
	private LocalDateTime dataInicialEvento;
	private LocalDateTime dataFinalEvento;
	private String local;
	private int organizador;
	private int vagasDeInscricao;
	
	public Evento() {
		super();
	}

	public Evento(String titulo, String descricao, LocalDateTime dataInicioDaInscricao, LocalDateTime dataFinalDaInscricao,
			LocalDateTime dataInicialEvento, LocalDateTime dataFinalEvento, String local, int organizador,
			int vagasDeInscricao) {
		setTitulo(titulo);
		setDescricao(descricao);
		setDataInicioDaInscricao(dataInicioDaInscricao);
		setDataFinalDaInscricao(dataFinalDaInscricao);
		setDataInicialEvento(dataInicialEvento);
		setDataFinalEvento(dataFinalEvento);
		setLocal(local);
		setOrganizador(organizador);
		setVagasDeInscricao(vagasDeInscricao);
	}



	public Evento(int id, String titulo, String descricao, LocalDateTime dataInicioDaInscricao,
			LocalDateTime dataFinalDaInscricao, LocalDateTime dataInicialEvento, LocalDateTime dataFinalEvento, String local,
			int organizador, int vagasDeInscricao) {
		this(titulo, descricao, dataInicioDaInscricao, dataFinalDaInscricao, dataInicialEvento, dataFinalEvento, 
				local, organizador, vagasDeInscricao);
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInicioDaInscricao() {
		return dataInicioDaInscricao;
	}

	public void setDataInicioDaInscricao(LocalDateTime dataInicioDaInscricao) {
		this.dataInicioDaInscricao = dataInicioDaInscricao;
	}

	public LocalDateTime getDataFinalDaInscricao() {
		return dataFinalDaInscricao;
	}

	public void setDataFinalDaInscricao(LocalDateTime dataFinalDaInscricao) {
		this.dataFinalDaInscricao = dataFinalDaInscricao;
	}

	public LocalDateTime getDataInicialEvento() {
		return dataInicialEvento;
	}

	public void setDataInicialEvento(LocalDateTime dataInicialEvento) {
		this.dataInicialEvento = dataInicialEvento;
	}

	public LocalDateTime getDataFinalEvento() {
		return dataFinalEvento;
	}

	public void setDataFinalEvento(LocalDateTime dataFinalEvento) {
		this.dataFinalEvento = dataFinalEvento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getOrganizador() {
		return organizador;
	}

	public void setOrganizador(int organizador) {
		this.organizador = organizador;
	}

	public int getVagasDeInscricao() {
		return vagasDeInscricao;
	}

	public void setVagasDeInscricao(int vagasDeInscricao) {
		this.vagasDeInscricao = vagasDeInscricao;
	}
	
}
