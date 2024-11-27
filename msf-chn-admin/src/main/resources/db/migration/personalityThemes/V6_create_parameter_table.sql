CREATE TABLE IF NOT EXISTS `parameter` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del parámetro (clave primaria).',
  `code` varchar(64) NOT NULL COMMENT 'Código único que identifica al parámetro.',
  `root_id` int DEFAULT NULL COMMENT 'Clave foránea que referencia a un parámetro raíz, estableciendo una jerarquía de parámetros.',
  `name` varchar(512) NOT NULL COMMENT 'Nombre descriptivo del parámetro.',
  `value_param` varchar(1024) NOT NULL COMMENT 'Valor asociado al parámetro.',
  `is_active` tinyint unsigned DEFAULT '1' COMMENT 'Indicador de si el parámetro está activo (1) o inactivo (0).',
  `creator_user_id` varchar(128) NOT NULL COMMENT 'Identificador del usuario que creó el registro del parámetro.',
  `create_at` timestamp NOT NULL COMMENT 'Fecha y hora en que se creó el registro del parámetro.',
  `updater_user_id` varchar(128) DEFAULT NULL COMMENT 'Identificador del usuario que actualizó el registro por última vez.',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT 'Fecha y hora de la última actualización del registro del parámetro.',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`),
  KEY `parameter_parameter_ibfk_1` (`root_id`),
  CONSTRAINT `parameter_parameter_ibfk_1` FOREIGN KEY (`root_id`) REFERENCES `parameter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
