package ec.diners.com.infrastructure.repositories.EntityLog;

import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.infrastructure.modelsDb.logs.EntityLogDbModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EntityLogMapper {
    @InheritConfiguration
    EntityLog toEntity(EntityLogDbModel model);
    @InheritConfiguration
    List<EntityLog> toEntities(List<EntityLogDbModel> models);
    @InheritConfiguration
    EntityLogDbModel toModel(EntityLog entity);
    @InheritConfiguration
    List<EntityLogDbModel> toModels(List<EntityLog> entities);
}

