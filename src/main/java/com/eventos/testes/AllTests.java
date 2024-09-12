package com.eventos.testes;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ConnectionFactoryTest.class, EventoDAOTest.class, UsuarioDAOTest.class })
public class AllTests {

}
