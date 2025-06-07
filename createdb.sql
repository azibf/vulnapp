-- Создание таблицы пользователей (users)
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);

-- Создание таблицы для хранения ролей (user_roles)
CREATE TABLE IF NOT EXISTS  user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Создание таблицы акций (promotions)
CREATE TABLE IF NOT EXISTS  promotions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    image BYTEA
);

-- Создание таблицы отзывов (feedbacks)
CREATE TABLE IF NOT EXISTS  feedbacks (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (id, username, password, email)  VALUES (1, 'admin', 'admin', 'admin@example.com');
INSERT INTO users (id, username, password, email) VALUES (2, 'user', 'user', 'user@example.com');
INSERT INTO user_roles (user_id, role) VALUES (1, 'ADMIN');
INSERT INTO user_roles (user_id, role) VALUES (2, 'USER');