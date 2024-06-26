CREATE DATABASE hastype;

USE hastype; 

create table User(
	id varchar(36) PRIMARY KEY,
    email varchar(30) NOT NULL UNIQUE,
    nome varchar(30) NOT NULL,
    senha varchar(10) NOT NULL
);

create table Quiz(
	id varchar(36) PRIMARY KEY NOT NULL,
    pontuacao Integer (2) NOT NULL,
    tempo_inicio Time,
    tempo_final Time, 
    user_id varchar(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id)
);

create table quiz_palavra(
	quiz_id varchar(36) NOT NULL,
	palavra_id Integer(2) NOT NULL,
	FOREIGN KEY (palavra_id) REFERENCES Palavra(id),
    FOREIGN KEY (quiz_id) REFERENCES Quiz(id)
);

create table palavra (
	id Integer(2) NOT NULL PRIMARY KEY,
	palavra_normal varchar(30) NOT NULL,
    palavra_traduzida varchar(30) NOT NULL
);

create table ranking_tempo(
	id varchar(36) NOT NULL PRIMARY KEY,
    user_id varchar(36) NOT NULL,
    user_name varchar(30) NOT NULL,
    quiz_id varchar(36) NOT NULL,
    tempo Integer(5) NOT NULL,
    foreign key (user_id) REFERENCES User(id),
    foreign key (quiz_id) REFERENCES Quiz(id)
);

create table ranking_pontuacao(
	id varchar(36) NOT NULL PRIMARY KEY,
    user_id varchar(36) NOT NULL,
    user_name varchar(30) NOT NULL,
    quiz_id varchar(36) NOT NULL,
    pontuacao integer(2) NOT NULL,
    foreign key (user_id) REFERENCES User(id),
    foreign key (quiz_id) REFERENCES Quiz(id)
);

select * from palavra;

select * from quiz;

select * from ranking_tempo;
    
select * from ranking_pontuacao;