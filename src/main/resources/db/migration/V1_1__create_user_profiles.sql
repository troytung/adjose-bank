
CREATE TABLE user_profiles (
username VARCHAR(50) PRIMARY KEY NOT NULL,
email VARCHAR(100) UNIQUE KEY NOT NULL,
phone_number VARCHAR(15),
created_at TIMESTAMP NOT NULL,
updated_at TIMESTAMP,
constraint fk_user_profiles_users foreign key(username) references users(username));
