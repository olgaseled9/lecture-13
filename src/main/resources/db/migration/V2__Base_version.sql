create table if not exists Orders
(
    order_id    bigint not null primary key,
    customer_id bigint not null Unique,
    quantity    int    not null,
    foreign key (customer_id) references Customers (customer_id)
);
