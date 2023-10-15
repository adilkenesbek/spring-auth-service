create table app_user (
     id bigserial primary key,
     email character varying(100) unique,
     name character varying(255),
     is_blocked boolean,
     password character varying(255)
);

create table role (
    id bigserial,
    name varchar(100)
);

insert into role (name) values ('ADMIN'), ('USER');

create table rel_app_user_role (
    app_user_id bigint,
    role_id bigint,
    foreign key (app_user_id) references app_user(id),
    foreign key (role_id) references role(id)
)