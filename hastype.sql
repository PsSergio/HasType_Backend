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
	user_id varchar(36) NOT NULL,
	FOREIGN KEY (quiz_id) REFERENCES quiz(id),
	FOREIGN KEY (user_id) REFERENCES user(id)
)