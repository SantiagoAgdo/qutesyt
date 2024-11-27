package ec.diners.com.infrastructure.repositories.user;

import ec.diners.com.domain.entities.user.User;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.user.UserDbModel;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserModelMapperImpl implements ModelMapper<User, UserDbModel> {

    private final UserMapper mapperInstance;

    public UserModelMapperImpl() {
        mapperInstance = Mappers.getMapper(UserMapper.class);
    }

    public User modelToEntity(UserDbModel model) {
        return mapperInstance.toEntity(model);
    }

    public List<User> modelsToEntities(List<UserDbModel> models) {
        return mapperInstance.toEntities(models);
    }

    public UserDbModel entityToModel(User entity) {
        return mapperInstance.toModel(entity);
    }

    public List<UserDbModel> entitiesToModels(List<User> entities) {
        return mapperInstance.toModels(entities);
    }
}