Create table tb_merchants
(
     id serial primary key,
    cnpj        varchar(14) not null unique,
    user_id integer not null,
    foreign key (user_id) references tb_users(id)
);