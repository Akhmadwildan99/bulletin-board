CREATE TABLE IF NOT EXISTS posts (
    id BIGSERIAL PRIMARY KEY  NOT NULL,
    author VARCHAR(255),
    title  VARCHAR(50),
    password TEXT,
    created_date TIMESTAMP WITHOUT TIME ZONE ,
    modified_date TIMESTAMP WITHOUT TIME ZONE
)