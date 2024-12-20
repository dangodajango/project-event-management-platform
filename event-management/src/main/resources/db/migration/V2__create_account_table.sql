CREATE TABLE account (
    id BIGSERIAL,
    name VARCHAR(255),
    email VARCHAR(255),
    profile_picture_url VARCHAR(255),
    password_hash VARCHAR(255),
    password_salt VARCHAR(255),
    account_type_id BIGSERIAL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (account_type_id) REFERENCES account_type(id)
);