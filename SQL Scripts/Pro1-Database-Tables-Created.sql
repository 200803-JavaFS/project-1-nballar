--CREATE DATABASE project1;

DROP TABLE IF EXISTS ers_user_roles;
DROP TABLE IF EXISTS ers_users;
DROP TABLE IF EXISTS ers_reimbursement_status;
DROP TABLE IF EXISTS ers_reimbursement_type;
DROP TABLE IF EXISTS ers_reimbursement;

CREATE TABLE ers_user_roles (
	ers_user_role_id SERIAL PRIMARY KEY,
	user_role VARCHAR(10) NOT NULL
);

CREATE TABLE ers_users (
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(50) NOT NULL,
	ers_password VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(100) NOT NULL,
	user_last_name VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) NOT NULL,
	user_role_id_fk INTEGER REFERENCES ers_user_roles(ers_user_role_id) NOT NULL,
	UNIQUE(ers_username, user_email)
);

CREATE TABLE ers_reimbursement_status (
	reimb_status_id SERIAL PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement_type (
	reimb_type_id SERIAL PRIMARY KEY,
	reimb_type VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement (
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount NUMERIC(7,2) NOT NULL,
	reimb_submitted TIMESTAMP NOT NULL,
	reimb_resolved TIMESTAMP,
	reimb_receipt BYTEA,
	reimb_description VARCHAR(250),
	reimb_author INTEGER REFERENCES ers_users(ers_users_id) NOT NULL,
	reimb_resolver INTEGER REFERENCES ers_users(ers_users_id),
	reimb_status_id_fk INTEGER REFERENCES ers_reimbursement_status(reimb_status_id) NOT NULL,
	reimb_type_id_fk INTEGER REFERENCES ers_reimbursement_type(reimb_type_id) NOT NULL
);
