package ec.diners.com.infrastructure.repositories.user;


import ec.diners.com.domain.entities.user.User;
import ec.diners.com.infrastructure.modelsDb.user.UserDbModel;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    @InheritConfiguration
    User toEntity(UserDbModel model);
    @InheritConfiguration
    List<User> toEntities(List<UserDbModel> models);
    @InheritConfiguration
    UserDbModel toModel(User entity);
    @InheritConfiguration
    List<UserDbModel> toModels(List<User> entities);
}

