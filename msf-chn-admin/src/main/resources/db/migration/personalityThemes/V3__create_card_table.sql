CREATE TABLE IF NOT EXISTS `card` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la tarjeta (clave primaria).',
  `uuid` varchar(128) NOT NULL COMMENT 'Identificador único global (UUID) para la tarjeta.',
  `name` varchar(512) NOT NULL COMMENT 'Nombre descriptivo de la tarjeta.',
  `external_code` varchar(512) DEFAULT NULL COMMENT 'Código externo asociado a la tarjeta, para integraciones o referencias externas.',
  `external_link` varchar(512) DEFAULT NULL COMMENT 'Enlace externo relacionado con la tarjeta, si corresponde.',
  `url_card` varchar(512) DEFAULT NULL COMMENT 'URL asociada a la tarjeta, utilizada para mostrar contenido relacionado.',
  `order_card` varchar(5) DEFAULT NULL COMMENT 'Orden de visualización de la tarjeta en el sistema.',
  `style_card` varchar(5) DEFAULT NULL COMMENT 'Estilo o categoría visual asignada a la tarjeta.',
  `is_active` tinyint unsigned DEFAULT '1' COMMENT 'Indicador de si la tarjeta está activa (1) o inactiva (0).',
  `creator_user_id` varchar(128) NOT NULL COMMENT 'Identificador del usuario que creó la tarjeta.',
  `create_at` timestamp NOT NULL COMMENT 'Fecha y hora en que la tarjeta fue creada.',
  `updater_user_id` varchar(128) DEFAULT NULL COMMENT 'Identificador del usuario que actualizó la tarjeta por última vez.',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT 'Fecha y hora de la última actualización de la tarjeta.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
