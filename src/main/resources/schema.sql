/*create table if not exists Ingredient(
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null,
    primary key (id)
);

create table if not exists Taco(
    id varchar(36) not null,
    name varchar(50) not null,
    createdAt timestamp not null,
    primary key (id)
);

create table if not exists Taco_Ingredients(
    taco varchar(36) not null,
    ingredient varchar(4) not null
);

alter table Taco_Ingredients add foreign key (taco) references Taco(id);
alter table Taco_Ingredients add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order(
    id varchar(36) not null,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null,
    primary key (id)
);

create table if not exists Taco_Order_Tacos(
    tacoOrder varchar(36) not null,
    taco varchar(36) not null
);

alter table Taco_Order_Tacos add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos add foreign key (taco) references Taco(id);*/