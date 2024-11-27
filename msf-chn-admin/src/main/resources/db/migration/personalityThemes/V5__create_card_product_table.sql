CREATE TABLE IF NOT EXISTS `card_product` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del producto de tarjeta (clave primaria).',
  `card_id` int NOT NULL COMMENT 'Clave foránea que referencia a la tabla `card` para asociar un producto con una tarjeta.',
  `code` varchar(128) NOT NULL COMMENT 'Código único que identifica al producto de la tarjeta.',
  `name` varchar(512) NOT NULL COMMENT 'Nombre descriptivo del producto asociado a la tarjeta.',
  `description` varchar(1024) DEFAULT NULL COMMENT 'Descripción detallada del producto asociado a la tarjeta.',
  `priority` int DEFAULT '999' COMMENT 'Nivel de prioridad del producto para ordenamiento (por defecto 999).',
  `url_animation` varchar(512) DEFAULT NULL COMMENT 'URL de la animación asociada al producto de la tarjeta, si existe.',
  `url_image` varchar(512) DEFAULT NULL COMMENT 'URL de la imagen asociada al producto de la tarjeta, si existe.',
  `is_active` tinyint unsigned DEFAULT '1' COMMENT 'Indicador de si el producto está activo (1) o inactivo (0).',
  `creator_user_id` varchar(50) NOT NULL COMMENT 'Identificador del usuario que creó el registro del producto.',
  `create_at` timestamp NOT NULL COMMENT 'Fecha y hora en que se creó el registro del producto.',
  `updater_user_id` varchar(50) DEFAULT NULL COMMENT 'Identificador del usuario que actualizó el registro por última vez.',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT 'Fecha y hora de la última actualización del registro del producto.',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `card_id` (`card_id`),
  CONSTRAINT `card_product_ibfk_1` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
