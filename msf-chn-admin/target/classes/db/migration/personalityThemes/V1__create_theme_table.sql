CREATE TABLE IF NOT EXISTS theme (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    is_active TINYINT(1) DEFAULT 1,
    updated_at TIMESTAMP NULL,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator_user_id VARCHAR(255),
    updater_user_id VARCHAR(255),
    CONSTRAINT unique_uuid_theme UNIQUE (uuid)
);


CREATE TABLE IF NOT EXISTS theme_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL,
    theme_id BIGINT NOT NULL,
    name VARCHAR(255),
    value VARCHAR(255),
    CONSTRAINT fk_theme_details_theme FOREIGN KEY (theme_id) REFERENCES theme(id),
    CONSTRAINT unique_uuid_theme_details UNIQUE (uuid)
);