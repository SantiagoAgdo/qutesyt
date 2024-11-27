CREATE TABLE IF NOT EXISTS `personalization` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del registro de personalización (clave primaria).',
  `diners_id` varchar(128) NOT NULL COMMENT 'Identificador único del usuario asociado, proporcionado por Diners Club.',
  `theme_id` varchar(128) DEFAULT NULL COMMENT 'Identificador del tema visual personalizado para el usuario.',
  `banner_ids` varchar(512) DEFAULT NULL COMMENT 'Lista de identificadores de banners asignados al usuario (formato JSON o CSV).',
  `module_your_experiences_ids` varchar(512) DEFAULT NULL COMMENT 'Lista de identificadores de módulos "Your Experiences" asociados al usuario (formato JSON o CSV).',
  `module_config` varchar(45) DEFAULT '0' COMMENT 'Configuración personalizada de módulos específicos para el usuario.',
  `recommended_by_restaurant` varchar(1024) DEFAULT NULL COMMENT 'Recomendaciones basadas en restaurantes personalizadas para el usuario.',
  `recommended_by_promotions` varchar(1024) DEFAULT NULL COMMENT 'Recomendaciones basadas en promociones personalizadas para el usuario.',
  `recommended_by_fashions` varchar(1024) DEFAULT NULL COMMENT 'Recomendaciones basadas en tendencias de moda personalizadas para el usuario.',
  `is_mode_challenger` tinyint unsigned DEFAULT '0' COMMENT 'Indicador de si el usuario está en modo "Challenger" (1 = Sí, 0 = No).',
  `registration_date` timestamp NOT NULL COMMENT 'Fecha y hora en que se registró la personalización del usuario.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `diners_id_UNIQUE` (`diners_id`),
  KEY `idx_diners_id` (`diners_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `personalization_mirror` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del registro (clave primaria).',
  `diners_id` varchar(128) NOT NULL COMMENT 'Identificador único del usuario asociado, proporcionado por Diners Club.',
  `theme_id` varchar(128) DEFAULT NULL COMMENT 'Identificador del tema visual personalizado para el usuario.',
  `banner_ids` varchar(512) DEFAULT NULL COMMENT 'Lista de identificadores de banners asignados al usuario (formato JSON o CSV).',
  `module_your_experiences_ids` varchar(512) DEFAULT NULL COMMENT 'Lista de identificadores de módulos "Your Experiences" asociados al usuario.',
  `module_config` varchar(45) DEFAULT '0' COMMENT 'Configuración personalizada de módulos específicos para el usuario.',
  `recommended_by_restaurant` varchar(1024) DEFAULT NULL COMMENT 'Recomendaciones basadas en restaurantes personalizadas para el usuario.',
  `recommended_by_promotions` varchar(1024) DEFAULT NULL COMMENT 'Recomendaciones basadas en promociones personalizadas para el usuario.',
  `recommended_by_fashions` varchar(1024) DEFAULT NULL COMMENT 'Recomendaciones basadas en tendencias de moda personalizadas para el usuario.',
  `is_mode_challenger` tinyint unsigned DEFAULT '0' COMMENT 'Indicador de si el usuario está en modo "Challenger" (1 = Sí, 0 = No).',
  `registration_date` timestamp NOT NULL COMMENT 'Fecha y hora en que se registró la personalización del usuario.',
  PRIMARY KEY (`id`),
  KEY `idx_diners_id` (`diners_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102201 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `personalization_changes` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del cambio de personalización (clave primaria).',
  `name` varchar(1024) NOT NULL COMMENT 'Nombre descriptivo del cambio de personalización.',
  `code` varchar(512) NOT NULL COMMENT 'Código único que identifica el cambio de personalización.',
  `state` varchar(128) NOT NULL COMMENT 'Estado actual del cambio de personalización (por ejemplo, activo, inactivo, finalizado).',
  `start_date` timestamp NULL DEFAULT NULL COMMENT 'Fecha y hora de inicio del cambio de personalización.',
  `end_date` timestamp NULL DEFAULT NULL COMMENT 'Fecha y hora de finalización del cambio de personalización.',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
