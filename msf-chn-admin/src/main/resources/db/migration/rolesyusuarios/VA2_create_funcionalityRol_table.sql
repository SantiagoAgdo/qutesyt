DROP TABLE IF EXISTS role_functionality;
DROP TABLE IF EXISTS functionality;

CREATE TABLE IF NOT EXISTS role (
                id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key of the role table',
                name VARCHAR(255) NOT NULL COMMENT 'Name of the role',
                description VARCHAR(255) COMMENT 'Description of the role',
                enabled BOOLEAN DEFAULT TRUE COMMENT 'Status of the role, true if enabled',
                created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the role was created',
                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the role was last updated'
);

CREATE TABLE IF NOT EXISTS functionality (
                 id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key of the functionality table',
                 name VARCHAR(255) NOT NULL COMMENT 'Name of the functionality',
                description VARCHAR(255) COMMENT 'Description of the functionality',
                created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the functionality was created',
                updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp when the functionality was last updated'
);

CREATE TABLE IF NOT EXISTS role_functionality (
                role_id BIGINT COMMENT 'Foreign key referencing role table',
                functionality_id BIGINT COMMENT 'Foreign key referencing functionality table',
                assigned_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT 'Timestamp when the functionality was assigned to the role',
                PRIMARY KEY (role_id, functionality_id),
                FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
                FOREIGN KEY (functionality_id) REFERENCES functionality(id) ON DELETE CASCADE
);