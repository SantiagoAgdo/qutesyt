package ec.diners.com.infrastructure.repositories.rol.mapper;

import ec.diners.com.domain.entities.rol.FunctionalityDto;
import ec.diners.com.infrastructure.modelsDb.rol.Functionality;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface FunctionalityMapper {

    @Mapping(source="id", target="id")
    FunctionalityDto toFunctionality(Functionality model);

    List<FunctionalityDto> toFunctionalitys(List<Functionality> functionalities);

    @InheritInverseConfiguration
    Functionality toFunctionalityModel(FunctionalityDto functionalityDto);

}
