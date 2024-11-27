package ec.diners.com.infrastructure.repositories.EntityLog;

import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.logs.EntityLogDbModel;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityLogModelMapperImpl implements ModelMapper<EntityLog, EntityLogDbModel> {

    private final EntityLogMapper mapperInstance;

    public EntityLogModelMapperImpl() {
        mapperInstance = Mappers.getMapper(EntityLogMapper.class);
    }

    public EntityLog modelToEntity(EntityLogDbModel model) {
        return mapperInstance.toEntity(model);
    }

    public List<EntityLog> modelsToEntities(List<EntityLogDbModel> models) {
        return mapperInstance.toEntities(models);
    }

    public EntityLogDbModel entityToModel(EntityLog entity) {
        return mapperInstance.toModel(entity);
    }

    public List<EntityLogDbModel> entitiesToModels(List<EntityLog> entities) {
        return mapperInstance.toModels(entities);
    }
}