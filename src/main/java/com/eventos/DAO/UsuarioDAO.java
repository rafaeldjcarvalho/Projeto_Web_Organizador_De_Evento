package com.eventos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.eventos.model.Inscrito;
import com.eventos.model.TipoUsuario;
import com.eventos.model.Usuario;

public class UsuarioDAO {
	
	private Connection connection;
	
	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public Usuario save(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuarios (nome, email, senha, tipo_usu, matricula, cpf, curso) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, usuario.getNomeCompleto());
			preparedStatement.setString(2, usuario.getEmail());
			preparedStatement.setString(3, usuario.getSenha());
			preparedStatement.setString(4, usuario.getTipoUsuario().toString());
			preparedStatement.setString(5, usuario.getMatricula());
			preparedStatement.setString(6, usuario.getCpf());
			preparedStatement.setString(7, usuario.getCurso());
			
			preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();

            int generatedId = resultSet.getInt(1);
            usuario.setId(generatedId);

            preparedStatement.close();
            resultSet.close();
		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		return usuario;
	}

	public Usuario update(Usuario usuario) {
		try {
			String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ?, tipo_usu = ?, matricula = ?, cpf = ?, curso = ? where id_usu = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getNomeCompleto());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getTipoUsuario().toString());
            preparedStatement.setString(5, usuario.getMatricula());
            preparedStatement.setString(6, usuario.getCpf());
            preparedStatement.setString(7, usuario.getCurso());
            preparedStatement.setInt(8, usuario.getId());

            preparedStatement.executeUpdate();

            preparedStatement.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return usuario;
	}

	public void delete(int id) {
		try {
			String sql = "DELETE FROM usuarios WHERE id_usu = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();

		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Usuario> findAll() {
		String sql = "SELECT id_usu, nome, email, tipo_usu, matricula, cpf, curso FROM usuarios";

        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_usu");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                TipoUsuario tipo = TipoUsuario.valueOf(rs.getString("tipo_usu"));
                String matricula = rs.getString("matricula");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");

                Usuario usuario = new Usuario(id, nome, matricula, cpf, curso, email, null, tipo);
                usuarios.add(usuario);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return usuarios;
	}

	public Optional<Usuario> findById(int id) {
		String sql = "SELECT id_usu, nome, email, tipo_usu, matricula, cpf, curso FROM usuarios Where id_usu = ?";

        Usuario usuario = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int pKey = rs.getInt("id_usu");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                TipoUsuario tipo = TipoUsuario.valueOf(rs.getString("tipo_usu"));
                String matricula = rs.getString("matricula");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");

                usuario = new Usuario(pKey, nome, matricula, cpf, curso, email, null, tipo);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(usuario);
	}
	
	public Optional<Usuario> findByEmail(String email) {
		String sql = "SELECT id_usu, nome, email, senha, tipo_usu, matricula, cpf, curso FROM usuarios Where email = ?";

        Usuario usuario = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id_usu");
                String nome = rs.getString("nome");
                String emailUsu = rs.getString("email");
                String senha = rs.getString("senha");
                TipoUsuario tipo = TipoUsuario.valueOf(rs.getString("tipo_usu"));
                String matricula = rs.getString("matricula");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");

                usuario = new Usuario(id, nome, matricula, cpf, curso, emailUsu, senha, tipo);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return Optional.ofNullable(usuario);
	}
	
	public List<Usuario> findByType(TipoUsuario tipo) {
		String sql = "SELECT id_usu, nome, email, tipo_usu, matricula, cpf, curso FROM usuarios WHERE tipo_usu = ?";

        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tipo.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id_usu");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(rs.getString("tipo_usu"));
                String matricula = rs.getString("matricula");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");

                Usuario usuario = new Usuario(id, nome, matricula, cpf, curso, email, null, tipoUsuario);
                usuarios.add(usuario);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return usuarios;
	}
	
	public List<Inscrito> findAllUsersInEvent(int id_evento) {
		String sql = "SELECT id_usu, nome, email, tipo_usu, matricula, cpf, curso, status FROM usuarios join participantes on usuarios.id_usu = participantes.usuario_id where participantes.evento_id = ?";

        List<Inscrito> usuarios = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_evento);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id_usu");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(rs.getString("tipo_usu"));
                String matricula = rs.getString("matricula");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");
                String status = rs.getString("status");

                Inscrito usuario = new Inscrito(id, nome, matricula, cpf, curso, email, null, tipoUsuario, status);
                usuarios.add(usuario);
            }

            preparedStatement.close();
            rs.close();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return usuarios;
	}
	
	public int saveUserInEvent(int id_evento, int id_usuario) {
		try {
			String sql = "INSERT INTO participantes (evento_id, usuario_id) VALUES (?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id_evento);
			preparedStatement.setInt(2, id_usuario);
			
			preparedStatement.executeUpdate();

            preparedStatement.close();
		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		return id_usuario;
	}
	
	public void deleteUserInEvent(int id_participacao) {
		try {
			String sql = "DELETE FROM participantes WHERE id_part = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_participacao);

            preparedStatement.executeUpdate();

            preparedStatement.close();

		} catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public int updateUserInEvent(int id_evento, int id_usuario, String status) {
		try {
			String sql = "UPDATE participantes SET status = ? where evento_id = ? AND usuario_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id_evento);
            preparedStatement.setInt(3, id_usuario);

            preparedStatement.executeUpdate();

            preparedStatement.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return id_usuario;
	}

}
