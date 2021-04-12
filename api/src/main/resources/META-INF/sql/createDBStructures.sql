DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS access_levels;

DROP SEQUENCE IF EXISTS users_seq;
DROP SEQUENCE IF EXISTS accounts_seq;
DROP SEQUENCE IF EXISTS access_levels_seq;

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

--------------------------------------------------------------------
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

--------------------------------------------------------------------
CREATE TABLE access_levels
(
    id         BIGINT PRIMARY KEY,
    level      VARCHAR(32) NOT NULL
        CONSTRAINT access_levels_level_values CHECK (level IN
                                                     ('level.admin', 'level.user')),
    account_id BIGINT      NOT NULL
        CONSTRAINT acc_lvl_account_fk REFERENCES accounts (id),
    enabled    BOOL        NOT NULL DEFAULT TRUE,
    version    BIGINT
        CONSTRAINT acc_lvl_version_gr0 CHECK (version >= 0),
    CONSTRAINT acc_lvl_level_account_pair_unique UNIQUE (level, account_id)
);

ALTER TABLE access_levels
    OWNER TO zzpjadmin;

CREATE SEQUENCE access_levels_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE access_levels_seq
    OWNER TO zzpjadmin;

CREATE
    INDEX access_levels_id_index ON access_levels (id);

CREATE
    INDEX accounts_account_id_index ON access_levels (account_id);


--------------------------------------------------------------------
CREATE TABLE chats
(
    id         BIGINT PRIMARY KEY,
    owner_id   BIGINT      NOT NULL
        CONSTRAINT chats_account_fk REFERENCES accounts (id),
    name       VARCHAR(30) NULL,
    created_at TIMESTAMPTZ NOT NULL,
    version    BIGINT
        CONSTRAINT chats_version_gr0 CHECK (version >= 0)
);

ALTER TABLE chats
    OWNER TO zzpjadmin;

CREATE SEQUENCE chats_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE chats_seq
    OWNER TO zzpjadmin;

CREATE
    INDEX chats_id_index ON chats (id);
CREATE
    INDEX chats_name_index ON chats (name);
CREATE
    INDEX chats_owner_id_index ON chats (owner_id);