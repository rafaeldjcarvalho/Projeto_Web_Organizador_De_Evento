package com.eventos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.eventos.model.Evento;

public class EventoDAO {
	
	private Connection connection;
	
	public EventoDAO(Connection connection) {
		this.connection = connection;
	}

	public Evento save(Evento evento) {
		try {
			String sql = "INSERT INTO eventos (titulo, descricao, data_inscricao_ini, data_inscricao_fim, data_inicio, data_fim, local, organizador_id, vagas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, evento.getTitulo());
			preparedStatement.setString(2, evento.getDescricao());
			preparedStatement.setString(3, evento.getDataInicioDaInscricao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			preparedStatement.setString(4, evento.getDataFinalDaInscricao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			preparedStatement.setString(5, evento.getDataInicialEvento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			preparedStatement.setString(6, evento.getDataFinalEvento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			preparedStatement.setString(7, evento.getLocal());
			preparedStatement.setInt(8, evento.getOrganizador());
			preparedStatement.setInt(9, evento.getVagasDeInscricao());
			
			preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            int generatedId = resultSet.getInt(1);
            evento.setId(generatedId);

            preparedStatement.close();
            resultSet.close();
		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		return evento;
	}

	public Evento update(Evento evento) {
		try {
			String sql = "UPDATE eventos SET titulo = ?, descricao = ?, data_inscricao_ini = ?, data_inscricao_fim = ?, data_inicio = ?, data_fim = ?, local = ?, organizador_id = ?, vagas = ? where id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, evento.getTitulo());
            preparedStatement.setString(2, evento.getDescricao());
            preparedStatement.setString(3, evento.getDataInicioDaInscricao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setString(4, evento.getDataFinalDaInscricao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setString(5, evento.getDataInicialEvento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setString(6, evento.getDataFinalEvento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setString(7, evento.getLocal());
            preparedStatement.setInt(8, evento.getOrganizador());
            preparedStatement.setInt(9, evento.getVagasDeInscricao());
            preparedStatement.setInt(10, evento.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return evento;
	}

	public void delete(int id) {
		try {
			String sql = "DELETE FROM eventos WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Evento> findAll() {
		String sql = "SELECT id, titulo, descricao, data_inscricao_ini, data_inscricao_fim, data_inicio, data_fim, local, organizador_id, vagas FROM eventos";

        List<Evento> eventos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDateTime dt_inscricao_ini = LocalDateTime.of(rs.getDate("data_inscricao_ini").toLocalDate(), rs.getTime("data_inscricao_ini").toLocalTime());
                LocalDateTime dt_inscricao_fim = LocalDateTime.of(rs.getDate("data_inscricao_fim").toLocalDate(), rs.getTime("data_inscricao_fim").toLocalTime());
                LocalDateTime dt_ini = LocalDateTime.of(rs.getDate("data_inicio").toLocalDate(), rs.getTime("data_inicio").toLocalTime());
                LocalDateTime dt_fim = LocalDateTime.of(rs.getDate("data_fim").toLocalDate(), rs.getTime("data_fim").toLocalTime());
                String local = rs.getString("local");
                int organizador = rs.getInt("organizador_id");
                int vagas = rs.getInt("vagas");

                Evento evento = new Evento(id, titulo, descricao, dt_inscricao_ini, dt_inscricao_fim, dt_ini, dt_fim, local, organizador, vagas);
                eventos.add(evento);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return eventos;
	}

	public Optional<Evento> findById(int id) {
		String sql = "SELECT id, titulo, descricao, data_inscricao_ini, data_inscricao_fim, data_inicio, data_fim, local, organizador_id, vagas FROM eventos Where id = ?";

        Evento evento = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int pKey = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDateTime dt_inscricao_ini = LocalDateTime.of(rs.getDate("data_inscricao_ini").toLocalDate(), rs.getTime("data_inscricao_ini").toLocalTime());
                LocalDateTime dt_inscricao_fim = LocalDateTime.of(rs.getDate("data_inscricao_fim").toLocalDate(), rs.getTime("data_inscricao_fim").toLocalTime());
                LocalDateTime dt_ini = LocalDateTime.of(rs.getDate("data_inicio").toLocalDate(), rs.getTime("data_inicio").toLocalTime());
                LocalDateTime dt_fim = LocalDateTime.of(rs.getDate("data_fim").toLocalDate(), rs.getTime("data_fim").toLocalTime());
                String local = rs.getString("local");
                int organizador = rs.getInt("organizador_id");
                int vagas = rs.getInt("vagas");

                evento = new Evento(pKey, titulo, descricao, dt_inscricao_ini, dt_inscricao_fim, dt_ini, dt_fim, local, organizador, vagas);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(evento);
	}
	
	public List<Evento> findAllEventofUser(int id_usuario) {
		String sql = "SELECT id_part, titulo, descricao, data_inscricao_ini, data_inscricao_fim, data_inicio, data_fim, local, organizador_id, vagas FROM eventos join participantes on eventos.id = participantes.evento_id where participantes.usuario_id = ?";

        List<Evento> eventos = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_usuario);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int id_part = rs.getInt("id_part");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDateTime dt_inscricao_ini = LocalDateTime.of(rs.getDate("data_inscricao_ini").toLocalDate(), rs.getTime("data_inscricao_ini").toLocalTime());
                LocalDateTime dt_inscricao_fim = LocalDateTime.of(rs.getDate("data_inscricao_fim").toLocalDate(), rs.getTime("data_inscricao_fim").toLocalTime());
                LocalDateTime dt_ini = LocalDateTime.of(rs.getDate("data_inicio").toLocalDate(), rs.getTime("data_inicio").toLocalTime());
                LocalDateTime dt_fim = LocalDateTime.of(rs.getDate("data_fim").toLocalDate(), rs.getTime("data_fim").toLocalTime());
                String local = rs.getString("local");
                int organizador = rs.getInt("organizador_id");
                int vagas = rs.getInt("vagas");

                Evento evento = new Evento(id_part, titulo, descricao, dt_inscricao_ini, dt_inscricao_fim, dt_ini, dt_fim, local, organizador, vagas);
                eventos.add(evento);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return eventos;
	}
	
	public int qtdVaga(int evento_id) {
		String sql = "select count(evento_id) from participantes where evento_id = ?";
		int qtd = -1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, evento_id);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            rs.next();
            qtd = rs.getInt(1);
            
            preparedStatement.close();
            rs.close();
            
		} catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
		return qtd;
	}
}
