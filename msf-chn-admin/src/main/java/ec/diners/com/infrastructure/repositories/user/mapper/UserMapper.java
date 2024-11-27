package ec.diners.com.infrastructure.repositories.user.mapper;

import ec.diners.com.application.dtos.request.user.CreateUserRequest;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.infrastructure.modelsDb.user.UserModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    UserDto toUser(UserModel model);

    List<UserDto> toUser(List<UserModel> userModels);

    @InheritInverseConfiguration
    UserModel toUserModel(UserDto userDto);

    @Mapping(target = "id", ignore = true) // Para evitar problemas con el ID al crear nuevos usuarios
    UserModel toUserModel(CreateUserRequest createUserRequest);
}

