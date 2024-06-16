use hastype

create table user(
	id varchar(36) PRIMARY KEY NOT NULL,
	email varchar(30) NOT NULL UNIQUE,
	nome varchar(40) NOT NULL,
	senha varchar(30) NOT NULL
)

create table quiz(
	id varchar(36) PRIMARY KEY NOT NULL,
	pontuacao integer(2) NOT NULL,
	tempo_inicio time NOT NULL,
	tempo_final time, 
	user_id varchar(36) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user(id)
)

create table palavra(
	id integer(2) PRIMARY KEY NOT NULL,
	palavra_normal varchar(30) NOT NULL,
	palavra_traduzida varchar(30) NOT NULL
)

create table quiz_palavra(
	palavra_id integer(2) NOT NULL, 
	quiz_id varchar(36) NOT NULL,
	FOREIGN KEY (palavra_id) REFERENCES palavra(id),
	FOREIGN KEY (quiz_id) REFERENCES quiz(id)
)

create table ranking_tempo(
	id varchar(36) PRIMARY KEY NOT NULL,
	quiz_id varchar(36) NOT NULL,
	user_id varchar(36),
	tempo integer(5) NOT NULL,
	FOREIGN KEY (quiz_id) REFERENCES quiz(id),
	FOREIGN KEY (user_id) REFERENCES user(id)
)

select * from ranking_tempo

INSERT INTO palavra (id, palavra_normal, palavra_traduzida) VALUES
(0, 'Software', 'Programa'),
(1, 'Hardware', 'Equipamento'),
(2, 'Bug', 'Erro'),
(3, 'Database', 'Banco de dados'),
(4, 'Algorithm', 'Algoritmo'),
(5, 'Interface', 'Interface'),
(6, 'Library', 'Biblioteca'),
(7, 'Function', 'Função'),
(8, 'Variable', 'Variável'),
(9, 'Loop', 'Laço'),
(10, 'Array', 'Vetor'),
(11, 'Class', 'Classe'),
(12, 'Object', 'Objeto'),
(13, 'Exception', 'Exceção'),
(14, 'Debug', 'Depurar'),
(15, 'Syntax', 'Sintaxe'),
(16, 'Script', 'Roteiro'),
(17, 'Query', 'Consulta'),
(18, 'Branch', 'Ramificação'),
(19, 'Repository', 'Repositório'),
(20, 'Encryption', 'Criptografia'),
(21, 'Backup', 'Cópia de Segurança'),
(22, 'Cloud', 'Nuvem'),
(23, 'Service', 'Serviço'),
(24, 'Framework', 'Estrutura'),
(25, 'Compiler', 'Compilador')

select * from quiz

