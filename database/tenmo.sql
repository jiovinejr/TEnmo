BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user, account, transfer;

DROP SEQUENCE IF EXISTS seq_user_id, seq_account_id, seq_transfer_id;

-- Sequence to start user_id values at 1001 instead of 1
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

-- Sequence to start account_id values at 2001 instead of 1
-- Note: Use similar sequences with unique starting values for additional tables
CREATE SEQUENCE seq_account_id
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE account (
	account_id int NOT NULL DEFAULT nextval('seq_account_id'),
	user_id int NOT NULL,
	balance numeric(13, 2) NOT NULL,
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_account_tenmo_user FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id)
);

CREATE SEQUENCE seq_transfer_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;

CREATE TABLE transfer (
	transfer_id int NOT NULL DEFAULT nextval('seq_transfer_id'),
	user_id int NOT NULL,
	sender_account_id int NOT NULL,
	receiver_account_id int NOT NULL,
	transfer_amount numeric(13, 2) NOT NULL,
	CONSTRAINT PK_transfer PRIMARY KEY (transfer_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES tenmo_user (user_id),
	CONSTRAINT FK_sender_account_id FOREIGN KEY (sender_account_id) REFERENCES account (account_id),
	CONSTRAINT FK_receiver_account_id FOREIGN KEY (receiver_account_id) REFERENCES account (account_id)
	
);

INSERT INTO tenmo_user (username, password_hash)
	VALUES ('Jimmy', '$2a$10$J1FuVz.tGMF0VacHm7RnjuWN9eaO/9Lrb0yittqg5qJ3cOvYGiRK.');
INSERT INTO tenmo_user (username, password_hash)
	VALUES ('Chrysti', '$2a$10$J1FuVz.tGMF0VacHm7RnjuWN9eaO/9Lrb0yittqg5qJ3cOvYGiRK.');	
INSERT INTO tenmo_user (username, password_hash)
	VALUES ('Eric', '$2a$10$J1FuVz.tGMF0VacHm7RnjuWN9eaO/9Lrb0yittqg5qJ3cOvYGiRK.');
INSERT INTO tenmo_user (username, password_hash)
	VALUES ('Captain Jack', '$2a$10$SoR0qdg.Pfb.P91dWikHGeXkQQIlGzatwVi8C9TE4VHn.ciMWVixO');
	
INSERT INTO account (user_id, balance)
	VALUES (1001, 1000.00),
		(1002, 10.00),
		(1003, 1000.0),
		(1004, 200.00);

COMMIT;

SELECT * FROM account;