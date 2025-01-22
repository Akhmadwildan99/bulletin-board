CREATE TABLE IF NOT EXISTS body_contents (
    id BIGSERIAL PRIMARY KEY  NOT NULL,
    post_id BIGINT,
    content TEXT,
    created_date TIMESTAMP WITHOUT TIME ZONE ,
    modified_date TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (post_id) REFERENCES posts(id)
)