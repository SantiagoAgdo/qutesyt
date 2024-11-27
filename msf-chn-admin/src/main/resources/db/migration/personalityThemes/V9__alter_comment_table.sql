ALTER TABLE priority_theme
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la prioridad del tema (clave primaria).',
    MODIFY COLUMN name VARCHAR(128) NOT NULL COMMENT 'Nombre del tema relacionado con la prioridad.',
    MODIFY COLUMN priority INT DEFAULT 999 NOT NULL COMMENT 'Nivel de prioridad asignado al tema (por defecto 999).';

ALTER TABLE segment
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del segmento (clave primaria).',
    MODIFY COLUMN code_segment INT NOT NULL DEFAULT -1 COMMENT 'Código numérico que identifica al segmento.',
    MODIFY COLUMN name VARCHAR(128) NOT NULL COMMENT 'Nombre descriptivo del segmento.';

ALTER TABLE product_theme
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del producto del tema (clave primaria).',
    MODIFY COLUMN code_product VARCHAR(128) NOT NULL COMMENT 'Código único que identifica el producto.',
    MODIFY COLUMN theme_id BIGINT NOT NULL COMMENT 'Clave foránea que referencia al tema asociado.',
    MODIFY COLUMN priority INT DEFAULT 1 NOT NULL COMMENT 'Nivel de prioridad asignado al producto dentro del tema.',
    MODIFY COLUMN create_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creación del producto del tema.',
    MODIFY COLUMN is_active BINARY(1) NOT NULL DEFAULT 1 COMMENT 'Indicador de si el producto está activo (1) o inactivo (0).',
    MODIFY COLUMN updated_at DATETIME NULL DEFAULT NULL COMMENT 'Fecha y hora de la última actualización del producto del tema.',
    MODIFY COLUMN creator_user_id VARCHAR(45) NULL DEFAULT NULL COMMENT 'Identificador del usuario que creó el producto.',
    MODIFY COLUMN updater_user_id VARCHAR(45) NULL DEFAULT NULL COMMENT 'Identificador del usuario que actualizó el producto.';

ALTER TABLE segment_theme
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del segmento del tema (clave primaria).',
    MODIFY COLUMN segment_id BIGINT DEFAULT -1 NOT NULL COMMENT 'Clave foránea que referencia al segmento asociado.',
    MODIFY COLUMN theme_segment_id BIGINT NOT NULL COMMENT 'Clave foránea que referencia al tema del segmento.',
    MODIFY COLUMN theme_campaign_id BIGINT NULL COMMENT 'Clave foránea que referencia a la campaña del tema, si aplica.',
    MODIFY COLUMN create_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora de creación del segmento del tema.',
    MODIFY COLUMN is_active BINARY(1) NOT NULL DEFAULT 1 COMMENT 'Indicador de si el segmento del tema está activo (1) o inactivo (0).',
    MODIFY COLUMN updated_at DATETIME NULL DEFAULT NULL COMMENT 'Fecha y hora de la última actualización del segmento del tema.',
    MODIFY COLUMN creator_user_id VARCHAR(45) NULL DEFAULT NULL COMMENT 'Identificador del usuario que creó el registro.',
    MODIFY COLUMN updater_user_id VARCHAR(45) NULL DEFAULT NULL COMMENT 'Identificador del usuario que actualizó el registro.';

ALTER TABLE theme
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del tema (clave primaria).',
    MODIFY COLUMN uuid VARCHAR(36) NOT NULL COMMENT 'Identificador único universal (UUID) del tema.',
    MODIFY COLUMN name VARCHAR(255) COMMENT 'Nombre descriptivo del tema.',
    MODIFY COLUMN description VARCHAR(255) COMMENT 'Descripción breve del tema.',
    MODIFY COLUMN is_active TINYINT(1) DEFAULT 1 COMMENT 'Indicador de si el tema está activo (1) o inactivo (0).',
    MODIFY COLUMN updated_at TIMESTAMP NULL COMMENT 'Fecha y hora de la última actualización del tema.',
    MODIFY COLUMN create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora en que se creó el tema.',
    MODIFY COLUMN creator_user_id VARCHAR(255) COMMENT 'Identificador del usuario que creó el tema.',
    MODIFY COLUMN updater_user_id VARCHAR(255) COMMENT 'Identificador del usuario que actualizó el tema.';

ALTER TABLE theme_details
    MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del detalle del tema (clave primaria).',
    MODIFY COLUMN uuid VARCHAR(36) NOT NULL COMMENT 'Identificador único universal (UUID) del detalle del tema.',
    MODIFY COLUMN theme_id BIGINT NOT NULL COMMENT 'Clave foránea que referencia al tema asociado.',
    MODIFY COLUMN name VARCHAR(255) COMMENT 'Nombre descriptivo del detalle del tema.',
    MODIFY COLUMN value VARCHAR(255) COMMENT 'Valor asignado al detalle del tema.';