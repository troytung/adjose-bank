
CREATE TABLE users (
username VARCHAR(50) PRIMARY KEY NOT NULL,
password VARCHAR(100) NOT NULL,
enabled TINYINT(1) NOT NULL);

CREATE TABLE authorities (
id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
authority varchar(50) NOT NULL,
constraint fk_authorities_users foreign key(username) references users(username));

create unique index ix_auth_username on authorities (username, authority);
