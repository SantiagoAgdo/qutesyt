DROP TABLE IF EXISTS users;

-- Creating the users table with comments on each column
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary key of the users table',
                                     name VARCHAR(128) NOT NULL COMMENT 'Name of the user',
    last_name VARCHAR(128) COMMENT 'Last name of the user',
    identification_number VARCHAR(60) NOT NULL COMMENT 'Identification number of the user, must be unique',
    identification_type VARCHAR(60) COMMENT 'Type of identification document',
    telephone_number VARCHAR(20) COMMENT 'Telephone number of the user',
    photo VARCHAR(60) COMMENT 'URL or path to the user photo',
    email VARCHAR(60) NOT NULL UNIQUE COMMENT 'Email address of the user, must be unique',
    pwd VARCHAR(256) NOT NULL COMMENT 'Password of the user, stored in encrypted format',
    token VARCHAR(256) COMMENT 'Authentication token for the user',
    enabled VARCHAR(20) COMMENT 'Status of the user account, e.g., active or inactive'
    );
