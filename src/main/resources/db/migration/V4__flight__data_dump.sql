alter table flight add column start_time time;
alter table flight add column end_time time;

insert into flight ("airline_id", "start", "destination", "layover", "date_of_journey", "duration_of_journey", "discount_id", "start_time", "end_time") values
(1, 'new delhi', 'mumbai', null, '2023-11-05'::date, '02:10:00'::time, null, '02:00:00'::time, '04:20:00'::time),
(1, 'new delhi', 'mumbai', null, '2023-11-11'::date, '02:15:00'::time, 1, '03:30:00'::time, '05:45:00'::time);