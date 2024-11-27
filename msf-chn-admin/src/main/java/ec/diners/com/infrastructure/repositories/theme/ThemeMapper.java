package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.infrastructure.modelsDb.theme.ThemeModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ThemeMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="uuid", target="uuid")
    @Mapping(source="name", target="name")
    @Mapping(source="description", target="description")
    @Mapping(source="creatorUserId", target="userCreate")
    @Mapping(source="updaterUserId", target="userUpdate")
    @Mapping(source="updatedAt", target="updatedAt")
    @Mapping(source="createdAt", target="createAt")
    @Mapping(source="isActive", target="isActive")
    Theme toTheme(ThemeModel model);

    @InheritInverseConfiguration
    ThemeModel toThemeModel(Theme theme);
}
