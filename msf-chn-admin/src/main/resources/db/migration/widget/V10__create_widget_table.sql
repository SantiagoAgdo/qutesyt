CREATE TABLE IF NOT EXISTS widget (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Identificador único del widget, clave primaria',
    name VARCHAR(255) NOT NULL COMMENT 'Nombre descriptivo del widget',
    order_priority INT NOT NULL COMMENT 'Orden de prioridad del widget (menor valor = mayor prioridad)',
    is_active TINYINT DEFAULT 1 COMMENT 'Indica si el widget está activo (1: activo, 0: inactivo)',
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha y hora en que el registro fue creado',
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Fecha y hora de la última actualización del registro',
    delete_at TIMESTAMP NULL COMMENT 'Fecha y hora en que el registro fue eliminado lógicamente',
    created_by VARCHAR(64) NULL COMMENT 'Usuario que creó el registro',
    updated_by VARCHAR(64) NULL COMMENT 'Usuario que actualizó el registro por última vez',
    deleted_by VARCHAR(64) NULL COMMENT 'Usuario que eliminó lógicamente el registro'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabla para la gestión de widgets con auditoría';
