package ec.diners.com.infrastructure.repositories.versionapp;



import ec.diners.com.domain.entities.versionapp.VersionApp;
import ec.diners.com.infrastructure.modelsDb.versionapp.VersionAppDbModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface VersionAppMapper {
    @InheritConfiguration
    VersionApp toEntity(VersionAppDbModel model);
    @InheritConfiguration
    List<VersionApp> toEntities(List<VersionAppDbModel> models);
    @InheritConfiguration
    VersionAppDbModel toModel(VersionApp entity);
    @InheritConfiguration
    List<VersionAppDbModel> toModels(List<VersionApp> entities);
}

