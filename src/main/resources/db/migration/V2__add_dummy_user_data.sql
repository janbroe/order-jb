insert into users(user_id, address, email, firstname, lastname, password, phone_number, role)
values (nextval('user_seq'),'adminstreet 12','admin@order.com','admin','Broeckx','pwd','0477885522', 1);

insert into users(user_id, address, email, firstname, lastname, password, phone_number, role)
values (nextval('user_seq'),'customerstreet 12','customer@order.com','customer','Broeckx','pwd','0477885522', 0);