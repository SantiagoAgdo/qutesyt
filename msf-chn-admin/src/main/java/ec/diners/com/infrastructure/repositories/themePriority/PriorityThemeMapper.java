package ec.diners.com.infrastructure.repositories.themePriority;

import ec.diners.com.domain.entities.themePriority.PriorityTheme;
import ec.diners.com.infrastructure.modelsDb.theme.PriorityThemeModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriorityThemeMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="name", target="name")
    @Mapping(source="priority", target="priority")
    PriorityTheme toPriorityTheme(PriorityThemeModel model);

    @InheritInverseConfiguration
    PriorityThemeModel totoPriorityThemeModel(PriorityTheme priorityTheme);

}
