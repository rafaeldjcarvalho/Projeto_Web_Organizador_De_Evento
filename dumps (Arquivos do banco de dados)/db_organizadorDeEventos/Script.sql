CREATE TABLE usuarios (
  id_usu INTEGER PRIMARY KEY AUTOINCREMENT,
  nome TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  senha TEXT NOT NULL,
  tipo_usu TEXT NOT NULL CHECK(tipo_usu IN ('ALUNO', 'PROFESSOR', 'ADMIN')),
  matricula TEXT UNIQUE,
  cpf TEXT NOT NULL UNIQUE,
  curso TEXT
);

CREATE TABLE eventos (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  titulo TEXT NOT NULL,
  descricao TEXT NOT NULL,
  data_inscricao_ini TEXT NOT NULL,
  data_inscricao_fim TEXT NOT NULL,
  data_inicio TEXT NOT NULL,
  data_fim TEXT NOT NULL,
  local TEXT NOT NULL,
  organizador_id INTEGER NOT NULL,
  vagas INTEGER NOT NULL,
  FOREIGN KEY (organizador_id) REFERENCES usuarios(id_usu)
);

CREATE TABLE participantes (
  id_part INTEGER PRIMARY KEY AUTOINCREMENT,
  evento_id INTEGER NOT NULL,
  usuario_id INTEGER NOT NULL,
  status TEXT NOT NULL DEFAULT 'AUSENTE' CHECK(status IN ('PRESENTE', 'AUSENTE')),
  UNIQUE (evento_id, usuario_id),
  FOREIGN KEY (evento_id) REFERENCES eventos(id),
  FOREIGN KEY (usuario_id) REFERENCES usuarios(id_usu)
);
