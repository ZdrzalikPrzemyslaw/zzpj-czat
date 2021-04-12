DROP TABLE IF EXISTS chats;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE account
(
    id BIGINT PRIMARY KEY,
    username VARCHAR(32) UNIQUE NOT NULL,
    -- CHAR(64) - skrot hasla bedzie mial stala dlugosc - 64 znkai
    password CHAR(64) NOT NULL,

)