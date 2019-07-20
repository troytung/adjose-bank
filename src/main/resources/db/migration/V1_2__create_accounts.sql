
CREATE TABLE accounts (
account_number VARCHAR(36) PRIMARY KEY NOT NULL,
username VARCHAR(50) NOT NULL,
currency VARCHAR(3) NOT NULL,
created_at TIMESTAMP NOT NULL,
updated_at TIMESTAMP,
constraint fk_accounts_user_profiles foreign key(username) references user_profiles(username));

create unique index ix_accounts_username_currency on accounts (username, currency);
