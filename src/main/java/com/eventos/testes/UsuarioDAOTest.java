package com.eventos.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.UsuarioDAO;
import com.eventos.model.TipoUsuario;
import com.eventos.model.Usuario;

class UsuarioDAOTest {
	
	// Caminho Feliz
	@Test
	public void testDelete() {
	    // Criar e salvar um novo usuário
	    Usuario usuario = new Usuario("Pedro Costa", "159753", "15975348620", "Direito", 
	                                  "pedro@email.com", "senha159", TipoUsuario.ALUNO);
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    Usuario usuarioSalvo = usuarioDAO.save(usuario);

	    // Deletar o usuário
	    usuarioDAO.delete(usuarioSalvo.getId());

	    // Verificar se o usuário foi deletado
	    Optional<Usuario> usuarioEncontrado = usuarioDAO.findById(usuarioSalvo.getId());
	    assertFalse(usuarioEncontrado.isPresent());
	}

	@Test
	public void testSave() {
	    // Criar uma instância do usuário
	    Usuario usuario = new Usuario("João Silva", "123456", "12345678910", "Ciência da Computação", 
	                                  "joao@email.com", "senha123", TipoUsuario.ALUNO);
	    
	    // Salvar o usuário
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    Usuario usuarioSalvo = usuarioDAO.save(usuario);

	    // Verificar se o ID foi gerado
	    assertNotNull(usuarioSalvo.getId());
	    assertEquals("João Silva", usuarioSalvo.getNomeCompleto());
	    assertEquals("joao@email.com", usuarioSalvo.getEmail());
	    
	    usuarioDAO.delete(usuarioSalvo.getId());
	}
	
	@Test
	public void testUpdate() {
	    // Criar e salvar um novo usuário
	    Usuario usuario = new Usuario("Maria Souza", "654321", "09876543210", "Engenharia", 
	                                  "maria@email.com", "senha456", TipoUsuario.ALUNO);
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    Usuario usuarioSalvo = usuarioDAO.save(usuario);

	    // Atualizar alguns dados do usuário
	    usuarioSalvo.setNomeCompleto("Maria Oliveira");
	    usuarioSalvo.setCurso("Administração");

	    // Chamar o método de update
	    Usuario usuarioAtualizado = usuarioDAO.update(usuarioSalvo);

	    // Verificar se os dados foram atualizados corretamente
	    assertEquals("Maria Oliveira", usuarioAtualizado.getNomeCompleto());
	    assertEquals("Administração", usuarioAtualizado.getCurso());
	    
	    usuarioDAO.delete(usuarioAtualizado.getId());
	}
	
	@Test
	public void testFindById() {
	    // Criar e salvar um novo usuário
	    Usuario usuario = new Usuario("Carlos Silva", "789123", "98765432109", "Medicina", 
	                                  "carlos@email.com", "senha789", TipoUsuario.ALUNO);
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    Usuario usuarioSalvo = usuarioDAO.save(usuario);

	    // Buscar o usuário pelo ID
	    Optional<Usuario> usuarioEncontrado = usuarioDAO.findById(usuarioSalvo.getId());

	    // Verificar se o usuário foi encontrado e se os dados estão corretos
	    assertTrue(usuarioEncontrado.isPresent());
	    assertEquals("Carlos Silva", usuarioEncontrado.get().getNomeCompleto());
	    
	    usuarioDAO.delete(usuarioSalvo.getId());
	}
	
	@Test
	public void testFindByEmail() {
	    // Criar e salvar um novo usuário
	    Usuario usuario = new Usuario("Ana Martins", "321654", "32165498720", "Arquitetura", 
	                                  "ana@email.com", "senha321", TipoUsuario.ALUNO);
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    usuarioDAO.save(usuario);

	    // Buscar o usuário pelo email
	    Optional<Usuario> usuarioEncontrado = usuarioDAO.findByEmail("ana@email.com");

	    // Verificar se o usuário foi encontrado e se os dados estão corretos
	    assertTrue(usuarioEncontrado.isPresent());
	    assertEquals("Ana Martins", usuarioEncontrado.get().getNomeCompleto());
	    
	    usuarioDAO.delete(usuarioEncontrado.get().getId());
	}
	
	// Caminho Ruim
	@Test
	public void testSaveUserWithNullFields() {
	    Usuario usuario = new Usuario(null, null, null, null, null, null, null);
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    assertThrows(RuntimeException.class, () -> {
	        usuarioDAO.save(usuario);
	    });
	}

	@Test
	public void testSaveDuplicateUser() {
	    Usuario usuario = new Usuario("Maria Clara", "123456", "12345678910", "Arquitetura", 
	                                  "maria@email.com", "senha123", TipoUsuario.ALUNO);
	    UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    usuarioDAO.save(usuario);
	    
	    assertThrows(RuntimeException.class, () -> {
	        usuarioDAO.save(usuario);
	    });
	    // Limpar dados de teste
	    usuarioDAO.delete(usuario.getId());
	}
	
	@Test
	public void testNotFindByUserId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO(ConnectionFactory.getConnection());
	    Optional<Usuario> user = usuarioDAO.findById(-1); // ID inexistente
	    assertFalse(user.isPresent());
	}

}
