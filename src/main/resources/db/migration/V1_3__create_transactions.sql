
CREATE TABLE transactions (
transaction_id VARCHAR(36) PRIMARY KEY NOT NULL,
transaction_type VARCHAR(20) NOT NULL,
account_number VARCHAR(36) NOT NULL,
amount DECIMAL(13, 4) NOT NULL,
transaction_date TIMESTAMP NOT NULL,
to_account_number VARCHAR(36),
from_account_number VARCHAR(36),
inverse_transaction_id VARCHAR(36),
constraint fk_transactions_account_number foreign key(account_number) references accounts(account_number),
constraint fk_transactions_to_account_number foreign key(to_account_number) references accounts(account_number),
constraint fk_transactions_from_account_number foreign key(from_account_number) references accounts(account_number),
constraint fk_transactions_inverse_transaction_id foreign key(inverse_transaction_id) references transactions(transaction_id));
