CREATE TABLE IF NOT EXISTS viewers (
         id BIGSERIAL PRIMARY KEY  NOT NULL,
         post_id BIGINT,
         created_date TIMESTAMP WITHOUT TIME ZONE ,
         FOREIGN KEY (post_id) REFERENCES posts(id)
    )