create database db_construcao;

use db_construcao;

create table pessoa(
	id int(11) not null auto_increment primary key,
    nome varchar(200) not null,
    cpf varchar(14) not null unique,
    email varchar(200) not null,
    telefone varchar(15) default null,
    sexo varchar(20) not null,
    dataNascimento date not null);
    
    INSERT INTO pessoa
		(`nome`,`cpf`,`email`,`telefone`,`sexo`,`dataNascimento`)
			VALUES("Pessoa Teste","111.111.111-11","teste@teste.com","(62)3222-2222",
			"MASCULINO","2021-01-01");

create table endereco(
	id int(11) not null auto_increment primary key,
    rua varchar(255) not null,
    numero varchar(5) not null,
    complemento varchar(100) default null,
    bairro varchar(200) not null,
    cep varchar(9) not null,
    cidade varchar(100) not null,
    estado varchar(100) not null,
    tipo varchar(30) default null,
    idPessoa int(11) not null,
    constraint foreign key (idPessoa) references pessoa(id));
    