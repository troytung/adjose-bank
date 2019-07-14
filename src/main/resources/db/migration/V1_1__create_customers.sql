
CREATE TABLE customers (
id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
username VARCHAR(50) UNIQUE KEY NOT NULL,
email VARCHAR(100) NOT NULL,
constraint fk_customers_users foreign key(username) references users(username));
