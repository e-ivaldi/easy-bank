CREATE SCHEMA easy_bank;

CREATE TYPE TEXT AS VARCHAR(65536);

CREATE TABLE easy_bank.users
(
	user_id INTEGER IDENTITY,
	username VARCHAR(16) NOT NULL,
	password VARCHAR(128) NOT NULL
);

CREATE TABLE easy_bank.accounts
(
	account_id INTEGER IDENTITY,	
	amount	DECIMAL(19,4) NOT NULL,
	user_id INTEGER NOT NULL,
	FOREIGN KEY (user_id) REFERENCES easy_bank.users(user_id)
);


-- CREATE TABLE easy_bank.credit_cards(
-- TODO: fill here	
--)


SET INITIAL SCHEMA easy_bank;

