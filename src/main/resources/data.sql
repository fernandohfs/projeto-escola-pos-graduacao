CREATE TABLE professor (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(150) NULL,
  email VARCHAR(150) NULL,
  data_nascimento DATE NULL,
  cpf CHAR(11) NULL,
  data_admissao DATETIME NULL,
  data_demissao DATETIME NULL,
  data_criacao DATETIME NULL,
  data_atualizacao DATETIME NULL,
  PRIMARY KEY(id)
);

CREATE TABLE curso (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(150) NULL,
  descricao VARCHAR(255) NULL,
  situacao CHAR(1) NULL,
  data_criacao DATETIME NULL,
  data_atualizacao DATETIME NULL,
  PRIMARY KEY(id)
);

CREATE TABLE aluno (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  nome VARCHAR(150) NULL,
  email VARCHAR(150) NULL,
  data_nascimento DATE NULL,
  cpf CHAR(11) NULL,
  data_criacao DATETIME NULL,
  data_atualizacao DATETIME NULL,
  PRIMARY KEY(id)
);

CREATE TABLE materia (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  professor_id INTEGER UNSIGNED NOT NULL,
  titulo VARCHAR(100) NULL,
  descricao VARCHAR(100) NULL,
  situacao CHAR(1) NULL,
  data_criacao DATETIME NULL,
  data_atualizacao DATETIME NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(professor_id) REFERENCES professor(id)
);

CREATE TABLE turma (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  aluno_id INTEGER UNSIGNED NOT NULL,
  curso_id INTEGER UNSIGNED NOT NULL,
  data_periodo_inicio DATE NULL,
  data_periodo_fim DATE NULL,
  observacao VARCHAR(150) NULL,
  situacao CHAR(1) NULL,
  data_criacao DATETIME NULL,
  data_atualizacao DATETIME NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(aluno_id) REFERENCES aluno(id),
  FOREIGN KEY(curso_id) REFERENCES curso(id)
);

CREATE TABLE materia_curso (
  materia_id INTEGER UNSIGNED NOT NULL,
  curso_id INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(materia_id, curso_id),
  FOREIGN KEY(materia_id) REFERENCES materia(materia_id),
  FOREIGN KEY(curso_id) REFERENCES curso(curso_id)
);


