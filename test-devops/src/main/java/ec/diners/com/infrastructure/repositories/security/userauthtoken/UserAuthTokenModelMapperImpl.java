package ec.diners.com.infrastructure.repositories.security.userauthtoken;

import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.security.UserAuthTokenDbModel;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAuthTokenModelMapperImpl implements ModelMapper<UserAuthToken, UserAuthTokenDbModel> {

    private final UserAuthTokenMapper mapperInstance;

    public UserAuthTokenModelMapperImpl() {
        mapperInstance = Mappers.getMapper(UserAuthTokenMapper.class);
    }

    public UserAuthToken modelToEntity(UserAuthTokenDbModel model) {
        return mapperInstance.toEntity(model);
    }

    public List<UserAuthToken> modelsToEntities(List<UserAuthTokenDbModel> models) {
        return mapperInstance.toEntities(models);
    }

    public UserAuthTokenDbModel entityToModel(UserAuthToken entity) {
        return mapperInstance.toModel(entity);
    }

    public List<UserAuthTokenDbModel> entitiesToModels(List<UserAuthToken> entities) {
        return mapperInstance.toModels(entities);
    }
}