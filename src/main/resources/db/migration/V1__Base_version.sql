create table if not exists customers
(
    customer_id bigint      not null primary key,
    first_name  varchar(25) not null,
    last_name   varchar(30) not null
);