package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.ThemeDetails;
import ec.diners.com.infrastructure.modelsDb.theme.ThemeDetailModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThemeDetailsMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="uuid", target="uuid")
    @Mapping(source="name", target="name")
    @Mapping(source="value", target="value")
    ThemeDetails toThemeDetails(ThemeDetailModel themeDetailModel);

    @InheritInverseConfiguration
    ThemeDetailModel toThemeDetailsModel(ThemeDetails themeDetails);

}
