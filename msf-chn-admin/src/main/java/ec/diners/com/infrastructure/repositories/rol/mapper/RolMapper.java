package ec.diners.com.infrastructure.repositories.rol.mapper;

import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.infrastructure.modelsDb.rol.RoleModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(source="id", target="roleId")
    RoleDto toRole(RoleModel model);

    List<RoleDto> toRoles(List<RoleModel> roleModels);

    @InheritInverseConfiguration
    RoleModel toRoleModel(RoleDto roleDto);

}
