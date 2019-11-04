/* Para limpar as tabelas se necessário, depois de dropar as tabelas execute o script data.sql */
DROP TABLE materia_curso;
DROP TABLE aluno_turma;
DROP TABLE turma;
DROP TABLE curso;
DROP TABLE aluno;
DROP TABLE materia;
DROP TABLE professor;

/* Popular Professores */
INSERT INTO professor(nome, email, data_nascimento, cpf, data_admissao, data_demissao, data_criacao, data_atualizacao)
VALUES
('Maria Vitoria', 'maria@escola.com',  '1990-01-23', '00245678915', '2010-08-13', null, NOW(), NOW()),
('Fernando Costa', 'fernandocosta@escola.com',  '1989-06-30', '00678937933', '2010-08-25', null, NOW(), NOW()),
('Juliana Batista', 'julianabatista@escola.com',  '1980-10-15', '00977533612', '2015-03-07', null, NOW(), NOW());

/* Popular Materias */
INSERT INTO materia(titulo, descricao, situacao, data_criacao, data_atualizacao)
VALUES
('Lógica de programação I', 'Essa matéria ensina os fundamentos da programação basica', 'A', NOW(), NOW()),
('Lógica de programação II', 'Essa matéria avança no aprendizado sobre solucionar problemas com a lógica e programação', 'A', NOW(), NOW()),
('Cálculo I', 'Vai ser ensinado o básico de cálculo', 'A', NOW(), NOW());

/* Popular Cursos */
INSERT INTO curso(titulo, descricao, situacao, data_criacao, data_atualizacao)
VALUES
('Sistemas de Informção', 'O curso é para desenvolvimento de sistemas, como também para analise de sistema ou qualquer área dentro da TI', 'A', NOW(), NOW()),
('Ciência da Computação', 'O curso é para formar profissionais que buscam resolver problemas computacionais', 'A', NOW(), NOW());

/* Popula Alunos */
INSERT INTO aluno(nome, email, data_nascimento, cpf, data_criacao, data_atualizacao)
VALUES
('João Vitor', 'joaovitor@gmail.com', '1995-06-12', '12345678911', NOW(), NOW()),
('Luiz Gabriel', 'luizgabriel@gmail.com', '2000-07-23', '03345678911', NOW(), NOW());

/* Popular Turma */
INSERT INTO turma(curso_id, data_periodo_inicio, data_periodo_fim, situacao, data_criacao, data_atualizacao)
VALUES
(1, '2019-01-01', '2022-12-07', 'A', NOW(), NOW()),
(2, '2019-01-01', '2022-12-07', 'A', NOW(), NOW());