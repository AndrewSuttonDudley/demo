use demo;

create table if not exists credit_requests (
    id int not null auto_increment,
    end_date datetime null,
    pdf_report_id varchar(255) null,
    request_date datetime not null,
    source varchar(255) not null,
    ssn varchar(255) not null,
    start_date datetime not null,
    status varchar(255) not null,
    primary key (id)
);