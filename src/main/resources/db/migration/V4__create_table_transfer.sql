create table tb_transfers (
    id serial primary key,
    payer_id int not null,
    payee_id int not null,
    value_transfer numeric(19,2) not null,
    transfered_at timestamp not null,
    foreign key (payer_id) references tb_users(id),
    foreign key (payee_id) references tb_users(id)
);