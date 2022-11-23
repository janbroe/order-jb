create table users
(
    user_id      bigint not null
        primary key,
    address      varchar(255),
    email        varchar(255),
    firstname    varchar(255),
    lastname     varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    role         integer
);

create sequence user_seq start with 1 increment by 1 cycle;

create table orders
(
    order_id    bigint           not null
        primary key,
    order_price double precision not null,
    user_id     bigint
        constraint fk_user_id
            references users
);

create sequence orders_seq start with 1 increment by 1 cycle;


create table item
(
    id          bigint           not null
        primary key,
    amount      integer          not null,
    description varchar(255),
    name        varchar(255),
    price       double precision not null
);

create sequence item_seq start with 1 increment by 1 cycle;

create table item_group
(
    id               bigint           not null
        primary key,
    amount           integer          not null,
    item_price       double precision not null,
    selected_item_id bigint,
    shipping_date    date,
    item_id          bigint
        constraint fk_item_id
            references item,
    order_id         bigint
        constraint fk_order_id
            references orders
);

create sequence itemgroup_seq start with 1 increment by 1 cycle;



