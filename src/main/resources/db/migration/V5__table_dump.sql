create table if not exists payment (
 transaction_id serial primary key,
 net_amount float,
 amount_paid float,
 discount smallint,
 transaction_status varchar(30)
);

create table if not exists ticket (
    ticket_id serial primary key,
    passenger_id int not null,
    transaction_id int not null,
    airline_id int not null,
    constraint fk_passenger_id foreign key (passenger_id) references passenger(passenger_id),
    constraint fk_transaction_id foreign key (transaction_id) references payment(transaction_id),
    constraint fk_airline_id foreign key (airline_id) references airline(airline_id)
);

create table if not exists passenger_view_flight (
    flight_id int,
    passenger_id int,
    primary key(flight_id, passenger_id)
);