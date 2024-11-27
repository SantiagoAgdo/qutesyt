package ec.diners.com.infrastructure.repositories.rol.mapper;

import ec.diners.com.domain.entities.rol.RoleFuncionalityDto;
import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionality;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RolMapper.class, FunctionalityMapper.class})
public interface RolFuncionalityMapper {

    @Mapping(source = "id.roleId", target = "id.roleId")
    @Mapping(source = "id.functionalityId", target = "id.functionalityId")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "functionality", target = "functionality")
    RoleFuncionalityDto toRoleFuncionality(RoleFunctionality model);

    List<RoleFuncionalityDto> toRolesFuncionality(List<RoleFunctionality> roleModels);

    @InheritInverseConfiguration
    @Mapping(source = "role", target = "role")
    @Mapping(source = "functionality", target = "functionality")
    RoleFunctionality toRoleFuncionalityModel(RoleFuncionalityDto roleFuncionalityDto);
}
