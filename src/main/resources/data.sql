insert into guest(id, name) values(null, 'Roger Federer');
insert into guest(id, name) values(null, 'Rafael Nadal');

insert into tennis_court(id, name) values(null, 'Roland Garros - Court Philippe-Chatrier');
insert into tennis_court(id, name) values(null, 'Clube Minas - Quadra 15');


insert
    into
        schedule
        (id, start_date_time, end_date_time, tennis_court_id)
    values
        (null, '2020-12-20T20:00:00.0', '2020-02-20T21:00:00.0', 1);
        
insert into RESERVATION(ID,RESERVATION_STATUS,VALUE,GUEST_ID,SCHEDULE_ID) values (null,1,250.22,1,1)