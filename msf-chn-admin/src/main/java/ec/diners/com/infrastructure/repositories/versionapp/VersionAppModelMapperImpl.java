package ec.diners.com.infrastructure.repositories.versionapp;


import ec.diners.com.domain.entities.versionapp.VersionApp;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.versionapp.VersionAppDbModel;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VersionAppModelMapperImpl implements ModelMapper<VersionApp, VersionAppDbModel> {

    private final VersionAppMapper mapperInstance;

    public VersionAppModelMapperImpl() {
        mapperInstance = Mappers.getMapper(VersionAppMapper.class);
    }

    public VersionApp modelToEntity(VersionAppDbModel model) {
        return mapperInstance.toEntity(model);
    }

    public List<VersionApp> modelsToEntities(List<VersionAppDbModel> models) {
        return mapperInstance.toEntities(models);
    }

    public VersionAppDbModel entityToModel(VersionApp entity) {
        return mapperInstance.toModel(entity);
    }

    public List<VersionAppDbModel> entitiesToModels(List<VersionApp> entities) {
        return mapperInstance.toModels(entities);
    }
}