DROP TABLE IF EXISTS chat_messages;
DROP TABLE IF EXISTS chat_users;
DROP TABLE IF EXISTS chats;
DROP TABLE IF EXISTS access_levels;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;

DROP SEQUENCE IF EXISTS chat_messages_seq;
DROP SEQUENCE IF EXISTS chat_users_seq;
DROP SEQUENCE IF EXISTS chats_seq;
DROP SEQUENCE IF EXISTS access_levels_seq;
DROP SEQUENCE IF EXISTS accounts_seq;
DROP SEQUENCE IF EXISTS users_seq;
--------------------------------------------------------------------
CREATE TABLE accounts
(
    id       BIGINT PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    -- CHAR(64) - skrot hasla bedzie mial stala dlugosc - 64 znaki
    password CHAR(64)           NOT NULL,
    enabled  BOOL               NOT NULL DEFAULT TRUE,
    version  BIGINT             NOT NULL
        CONSTRAINT acc_version_gr0 CHECK (version >= 0)
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
CREATE TABLE users
(
    id           BIGINT PRIMARY KEY,
    email        VARCHAR(100) NOT NULL
        CONSTRAINT acc_email_unique UNIQUE,
    first_name   VARCHAR(50)  NOT NULL,
    last_name    VARCHAR(80)  NOT NULL,
    phone_number VARCHAR(15),
    language     VARCHAR(35),
    account_id  BIGINT UNIQUE      NOT NULL
        CONSTRAINT users_account_id_fk REFERENCES accounts (id)
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
        CONSTRAINT chats_accounts_fk REFERENCES accounts (id),
    name       VARCHAR(30) NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
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

--------------------------------------------------------------------
CREATE TABLE chat_messages
(
    id         BIGINT PRIMARY KEY,
    chat_id    BIGINT      NOT NULL
        CONSTRAINT chat_messages_chats_fk REFERENCES chats (id),
    account_id BIGINT      NOT NULL
        CONSTRAINT chat_messages_accounts_fk REFERENCES accounts (id),
    text       VARCHAR(4096),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE chat_messages
    OWNER TO zzpjadmin;

CREATE SEQUENCE chat_messages_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE chat_messages_seq
    OWNER TO zzpjadmin;

CREATE
    INDEX chat_messages_id_index ON chat_messages (id);
CREATE
    INDEX chat_messages_chat_index ON chat_messages (chat_id);
CREATE
    INDEX chat_messages_account_index ON chat_messages (account_id);

--------------------------------------------------------------------
CREATE TABLE chat_users
(
    id         BIGINT PRIMARY KEY,
    chat_id    BIGINT NOT NULL
        CONSTRAINT chat_users_chats_fk REFERENCES chats (id),
    account_id BIGINT NOT NULL
        CONSTRAINT chat_users_accounts_fk REFERENCES accounts (id),
    enabled bool not null default true,
    UNIQUE (chat_id, account_id)
);

ALTER TABLE chat_users
    OWNER TO zzpjadmin;

CREATE SEQUENCE chat_users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER SEQUENCE chat_users_seq
    OWNER TO zzpjadmin;

CREATE
    INDEX chat_users_id_index ON chat_users (id);
CREATE
    INDEX chat_users_chat_index ON chat_users (chat_id);
CREATE
    INDEX chat_users_account_index ON chat_users (account_id);