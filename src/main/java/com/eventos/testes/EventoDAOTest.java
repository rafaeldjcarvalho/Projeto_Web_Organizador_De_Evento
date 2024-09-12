package com.eventos.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.eventos.DAO.ConnectionFactory;
import com.eventos.DAO.EventoDAO;
import com.eventos.model.Evento;

class EventoDAOTest {
	
	// Caminho Feliz
	@Test
	public void testDeleteEvento() {
	    EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
	    Evento evento = eventoDAO.save(new Evento("Evento Teste", "Descrição Teste", LocalDateTime.now(), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Local Teste", 1, 50));

	    eventoDAO.delete(evento.getId());

	    Optional<Evento> deletedEvento = eventoDAO.findById(evento.getId());
	    assertFalse(deletedEvento.isPresent());
	}

	@Test
	public void testSaveEvento() {
		LocalDateTime inicioInscricao = LocalDateTime.now();
		LocalDateTime FimInscricao = LocalDateTime.now().plusDays(5);
		LocalDateTime inicioEvento = LocalDateTime.now().plusDays(10);
		LocalDateTime fimEvento = LocalDateTime.now().plusDays(11);
		
	    Evento evento = new Evento("Evento Teste", "Descrição Teste", inicioInscricao, FimInscricao, inicioEvento, fimEvento, "Local Teste", 1, 50);
	    EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection()); 

	    Evento savedEvento = eventoDAO.save(evento);
	    
	    // Demais assertEquals para campos
	    assertNotNull(savedEvento.getId());
	    assertEquals("Evento Teste", savedEvento.getTitulo());
	    assertEquals("Descrição Teste", savedEvento.getDescricao());
	    assertEquals(inicioInscricao, savedEvento.getDataInicioDaInscricao());
	    assertEquals(FimInscricao, savedEvento.getDataFinalDaInscricao());
	    assertEquals(inicioEvento, savedEvento.getDataInicialEvento());
	    assertEquals(fimEvento, savedEvento.getDataFinalEvento());
	    assertEquals("Local Teste", savedEvento.getLocal());
	    assertEquals(1, savedEvento.getOrganizador());
	    assertEquals(50, savedEvento.getVagasDeInscricao());
	    
	    eventoDAO.delete(savedEvento.getId());
	}
	
	@Test
	public void testUpdateEvento() {
	    EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
	    Evento evento = eventoDAO.save(new Evento("Evento Teste", "Descrição Teste", LocalDateTime.now(), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Local Teste", 1, 50));

	    evento.setTitulo("Evento Atualizado");
	    Evento updatedEvento = eventoDAO.update(evento);

	    assertEquals("Evento Atualizado", updatedEvento.getTitulo());
	    
	    eventoDAO.delete(evento.getId());
	}
	
	@Test
	public void testFindById() {
	    EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
	    Evento evento = eventoDAO.save(new Evento("Evento Teste", "Descrição Teste", LocalDateTime.now(), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(11), "Local Teste", 1, 50));

	    Optional<Evento> foundEvento = eventoDAO.findById(evento.getId());

	    assertTrue(foundEvento.isPresent());
	    assertEquals("Evento Teste", foundEvento.get().getTitulo());
	    
	    eventoDAO.delete(evento.getId());
	}
	
	// Caminho Ruim
	@Test
	public void testSaveEventoWithInvalidTitulo() {
	    LocalDateTime inicioInscricao = LocalDateTime.now();
	    LocalDateTime fimInscricao = LocalDateTime.now().plusDays(5);
	    LocalDateTime inicioEvento = LocalDateTime.now().plusDays(10);
	    LocalDateTime fimEvento = LocalDateTime.now().plusDays(11);

	    // Evento com título vazio
	    Evento evento = new Evento(null, "Descrição Teste", inicioInscricao, fimInscricao, inicioEvento, fimEvento, "Local Teste", 1, 50);
	    EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());

	    assertThrows(RuntimeException.class, () -> {
	        eventoDAO.save(evento);
	    });
	}
	
	@Test
	public void testFindEventoByIdNotFound() {
	    EventoDAO eventoDAO = new EventoDAO(ConnectionFactory.getConnection());
	    Optional<Evento> evento = eventoDAO.findById(-1); // ID inexistente
	    assertFalse(evento.isPresent());
	}

}
