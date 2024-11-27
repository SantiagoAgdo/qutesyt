DROP TABLE IF EXISTS role;

CREATE TABLE IF NOT EXISTS role (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key of the role table',
                        name VARCHAR(255) NOT NULL COMMENT 'Name of the role',
                        description VARCHAR(255) COMMENT 'Description of the role',
                        enabled BOOLEAN DEFAULT TRUE COMMENT 'Status of the role, true if enabled',
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the role was created',
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the role was last updated'
);