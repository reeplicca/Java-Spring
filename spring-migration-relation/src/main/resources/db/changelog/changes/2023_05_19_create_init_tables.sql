create table brands(
    id serial primary key ,
    name varchar(50)
);

create table items(
    id serial primary key ,
    name varchar(50),
    price int,
    amount int,
    brands_id int,
    constraint fk_items_brands
                  foreign key (brands_id)
                  references brands(id)
);