CREATE TABLE IF NOT EXISTS `card_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del tipo de tarjeta (clave primaria).',
  `card_id` int NOT NULL COMMENT 'Clave foránea que referencia a la tabla `card` para asociar un tipo con una tarjeta.',
  `code` varchar(128) NOT NULL COMMENT 'Código único que identifica al tipo de tarjeta.',
  `name` varchar(512) NOT NULL COMMENT 'Nombre descriptivo del tipo de tarjeta.',
  `description` varchar(1024) DEFAULT NULL COMMENT 'Descripción adicional sobre el tipo de tarjeta.',
  `is_active` tinyint unsigned DEFAULT '1' COMMENT 'Indicador de si el tipo de tarjeta está activo (1) o inactivo (0).',
  `creator_user_id` varchar(50) NOT NULL COMMENT 'Identificador del usuario que creó el registro de tipo de tarjeta.',
  `create_at` timestamp NOT NULL COMMENT 'Fecha y hora en que se creó el registro de tipo de tarjeta.',
  `updater_user_id` varchar(50) DEFAULT NULL COMMENT 'Identificador del usuario que actualizó el registro por última vez.',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT 'Fecha y hora de la última actualización del registro de tipo de tarjeta.',
  PRIMARY KEY (`id`),
  KEY `fk_card_type_card2` (`card_id`),
  CONSTRAINT `fk_card_type_card2` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
