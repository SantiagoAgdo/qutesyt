CREATE TABLE IF NOT EXISTS priority_theme (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    priority INT DEFAULT 999 NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS segment (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code_segment INT NOT NULL DEFAULT -1,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4  COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS product_theme (
    id BIGINT NOT NULL AUTO_INCREMENT,
    code_product VARCHAR(128) NOT NULL,
    theme_id BIGINT NOT NULL,
    priority INT DEFAULT 1 NOT NULL,
    create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active binary(1) NOT NULL DEFAULT 1,
    updated_at datetime NULL DEFAULT NULL,
    creator_user_id varchar(45) NULL DEFAULT NULL,
    updater_user_id varchar(45) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE (code_product),
    FOREIGN KEY(theme_id)
        REFERENCES theme(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS segment_theme (
    id BIGINT NOT NULL AUTO_INCREMENT,
    segment_id BIGINT DEFAULT -1 NOT NULL,
    theme_segment_id BIGINT NOT NULL,
    theme_campaign_id BIGINT NULL,
    create_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active binary(1) NOT NULL DEFAULT 1,
    updated_at datetime NULL DEFAULT NULL,
    creator_user_id varchar(45) NULL DEFAULT NULL,
    updater_user_id varchar(45) NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (segment_id)
        REFERENCES segment(id),
    FOREIGN KEY(theme_segment_id)
        REFERENCES theme(id),
    FOREIGN KEY(theme_campaign_id)
        REFERENCES theme(id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4  COLLATE=utf8mb4_0900_ai_ci;