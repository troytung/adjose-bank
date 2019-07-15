
CREATE TABLE user_profiles (
id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
username VARCHAR(50) UNIQUE KEY NOT NULL,
email VARCHAR(100) UNIQUE KEY NOT NULL,
phone_number VARCHAR(15),
created_at TIMESTAMP NOT NULL,
updated_at TIMESTAMP,
constraint fk_customers_users foreign key(username) references users(username));
