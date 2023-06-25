create table place(
    id bigint not null auto_increment,
    name varchar(255) not null,
    slug varchar(255) not null,
    city varchar(255) not null,
    state varchar(255) not null,

    createdAt datetime not null,
    updatedAt datetime not null,

    primary key (id)
) engine=InnoDB default charset=UTF8MB4;