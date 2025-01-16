create table tb_users (
    id serial primary key,
    full_name varchar(255) not null,
    cpf varchar(11) not null unique,
    email varchar(255) not null unique,
    password varchar(255) not null,
    balance numeric(10, 2) not null default 0.0,
    created_at timestamp not null 
);