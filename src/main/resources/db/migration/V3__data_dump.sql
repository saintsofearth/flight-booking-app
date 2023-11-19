insert into airline ("name") values
    ('Indigo'),
    ('Air India'),
    ('Vistara'),
    ('Spice Jet');

insert into discount ("percentage", "valid_from", "valid_to") values
    (5, '2023-11-11'::date, '2023-11-20'::date),
    (10, '2023-12-22'::date, '2023-12-31'::date);
