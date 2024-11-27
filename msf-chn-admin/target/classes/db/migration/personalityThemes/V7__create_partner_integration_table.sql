CREATE TABLE IF NOT EXISTS `partner_integration` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del registro (clave primaria).',
  `diners_id` varchar(128) NOT NULL COMMENT 'Identificador único del socio, proporcionado por Diners Club.',
  `name` varchar(512) DEFAULT NULL COMMENT 'Nombre del socio asociado.',
  `theme_id` int DEFAULT NULL COMMENT 'Identificador del tema asociado al socio, relacionado con estilos o configuraciones visuales.',
  `card_products` varchar(512) NOT NULL COMMENT 'Lista de productos de tarjeta asociados al socio, en formato de texto (puede ser JSON o CSV).',
  `code_segment` int NOT NULL COMMENT 'Código del segmento al que pertenece el socio.',
  `is_registered` tinyint unsigned NOT NULL DEFAULT '0' COMMENT 'Indicador de si el socio está registrado (1) o no (0).',
  `last_longitude` decimal(60,30) DEFAULT '0.000000000000000000000000000000' COMMENT 'Última longitud registrada para el socio.',
  `last_latitude` decimal(60,30) DEFAULT '0.000000000000000000000000000000' COMMENT 'Última latitud registrada para el socio.',
  PRIMARY KEY (`id`),
  KEY `idx_diners_id` (`diners_id`)
) ENGINE=InnoDB AUTO_INCREMENT=108240 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
