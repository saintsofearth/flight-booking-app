create table if not exists user_profile (
    user_id serial primary key,
    username varchar(50),
    password varchar(64),
    fname varchar(50),
    lname varchar(50),
    gender smallint,
    contact_number varchar(15),
    date_of_birth date
)

create table if not exists passenger (
    passenger_id serial primary key,
    fname varchar(50),
    lname varchar(50),
    contact_number(15),
    date_of_birth date,
    user_profile_id int,
    flight_id int,
    constraint fk_flight_id foreign key (flight_id) references flight(flight_id) on delete no action
)