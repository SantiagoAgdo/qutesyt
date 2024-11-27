package ec.diners.com.infrastructure.repositories.security.userauthtoken;


import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.infrastructure.modelsDb.security.UserAuthTokenDbModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserAuthTokenMapper {
    @InheritConfiguration
    UserAuthToken toEntity(UserAuthTokenDbModel model);
    @InheritConfiguration
    List<UserAuthToken> toEntities(List<UserAuthTokenDbModel> models);
    @InheritConfiguration
    UserAuthTokenDbModel toModel(UserAuthToken entity);
    @InheritConfiguration
    List<UserAuthTokenDbModel> toModels(List<UserAuthToken> entities);
}

