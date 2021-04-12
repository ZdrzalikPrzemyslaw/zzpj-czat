DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;

CREATE TABLE users
(
    id           BIGINT PRIMARY KEY,
    email        VARCHAR(100) NOT NULL
        CONSTRAINT acc_email_unique UNIQUE,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(80)  NOT NULL,
    phone_number VARCHAR(15),
    language     VARCHAR(35)
);

ALTER TABLE users
    OWNER TO zzpjadmin;

CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE users_seq
    OWNER TO zzpjadmin;

CREATE
    INDEX users_id_index ON users (id);
CREATE
    INDEX users_first_name_index ON users (first_name);
CREATE
    INDEX users_last_name_index ON users (last_name);

CREATE TABLE accounts
(
    id       BIGINT PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    -- CHAR(64) - skrot hasla bedzie mial stala dlugosc - 64 znaki
    password CHAR(64)           NOT NULL,
    enabled  BOOL DEFAULT TRUE  NOT NULL,
    version  BIGINT             NOT NULL
        CONSTRAINT acc_version_gr0 CHECK (version >= 0),
    user_id  BIGINT             NOT NULL UNIQUE
        CONSTRAINT accounts_user_id_fk REFERENCES users (id)
);

ALTER TABLE accounts
    OWNER TO zzpjadmin;

CREATE SEQUENCE accounts_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE accounts_seq
    OWNER TO zzpjadmin;

CREATE
    INDEX accounts_id_index ON accounts (id);
CREATE
    INDEX accounts_username_index ON accounts (username);
