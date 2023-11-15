create table if not exists taco
(
    id         identity,
    name       varchar(50) not null,
    created_at timestamp   not null
);

create table if not exists ingredient
(
    id   varchar(4)  not null,
    name varchar(25) not null,
    type varchar(10) not null,
    PRIMARY KEY (id)
);

create table if not exists taco_ingredient
(
    id            identity,
    taco_id       bigint,
    ingredient_id varchar(4) not null,
    order_key     int,
    foreign key (taco_id) references taco,
    foreign key (ingredient_id) references ingredient
);

create table if not exists order_details
(
    id              identity,
    delivery_Name   varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City   varchar(50) not null,
    delivery_State  varchar(2)  not null,
    delivery_Zip    varchar(10) not null,
    cc_number       varchar(16) not null,
    cc_expiration   varchar(5)  not null,
    cc_cvv          varchar(3)  not null,
    placed_at       timestamp   not null
);

create table if not exists order_taco
(
    id       identity,
    order_id bigint,
    taco_id  bigint,
    foreign key (order_id) references order_details,
    foreign key (taco_id) references taco
);
