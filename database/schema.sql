DROP DATABASE IF EXISTS rental_db;
CREATE DATABASE rental_db;
USE rental_db;

CREATE TABLE users (
                id INT AUTO_INCREMENT NOT NULL,
                email VARCHAR(255),
                name VARCHAR(255),
                password VARCHAR(255),
                created_at TIMESTAMP,
                updated_at TIMESTAMP,
                PRIMARY KEY (id)
);


CREATE TABLE messages (
                id INT AUTO_INCREMENT NOT NULL,
                user_id INT NOT NULL,
                rental_id INT NOT NULL,
                message VARCHAR(2000),
                created_at TIMESTAMP,
                updated_at TIMESTAMP,
                PRIMARY KEY (id)
);


CREATE TABLE rentals (
                id INT AUTO_INCREMENT NOT NULL,
                owner_id INT NOT NULL,
                name VARCHAR(255),
                surface NUMERIC,
                price NUMERIC,
                picture VARCHAR(255),
                description VARCHAR(2000),
                created_at TIMESTAMP,
                updated_at TIMESTAMP,
                PRIMARY KEY (id)
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE rentals ADD CONSTRAINT users_rentals_fk
FOREIGN KEY (owner_id)
REFERENCES users (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE messages ADD CONSTRAINT users_messages_fk
FOREIGN KEY (user_id)
REFERENCES users (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE messages ADD CONSTRAINT rentals_messages_fk
FOREIGN KEY (rental_id)
REFERENCES rentals (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
