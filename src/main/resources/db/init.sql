CREATE TABLE app_role
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

-- Создание таблицы app_users
CREATE TABLE app_user
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    name        VARCHAR(50),
    salt        VARCHAR            NOT NULL,
    password    VARCHAR            NOT NULL,
    is_blocked  BOOLEAN,
    create_date TIMESTAMP
);

-- Создание таблицы app_user_roles
CREATE TABLE rel_app_user_role
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    role_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_user (id),
    FOREIGN KEY (role_id) REFERENCES app_role (id)
);

-- Создание ролей
insert into app_role (name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');