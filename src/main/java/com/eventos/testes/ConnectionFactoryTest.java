package com.eventos.testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.eventos.DAO.ConnectionFactory;

class ConnectionFactoryTest {

	 @Test
	    void testConnection() {
	        // Testa se a conexão foi criada com sucesso
	        try (Connection con = ConnectionFactory.getConnection()) {
	            assertNotNull(con, "Conexão deve ser estabelecida e não deve ser nula");
	        } catch (Exception e) {
	            fail("A conexão falhou: " + e.getMessage());
	        }
	    }
}
