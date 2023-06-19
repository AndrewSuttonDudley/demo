use demo;

create table if not exists credit_request (
    id int not null auto_increment,
    external_id varchar(255) not null,
    primary key (id),
    unique key external_id_unique (external_id)
);