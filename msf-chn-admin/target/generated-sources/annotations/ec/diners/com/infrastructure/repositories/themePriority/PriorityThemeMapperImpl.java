package ec.diners.com.infrastructure.repositories.themePriority;

import ec.diners.com.domain.entities.themePriority.PriorityTheme;
import ec.diners.com.infrastructure.modelsDb.theme.PriorityThemeModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class PriorityThemeMapperImpl implements PriorityThemeMapper {

    @Override
    public PriorityTheme toPriorityTheme(PriorityThemeModel model) {
        if ( model == null ) {
            return null;
        }

        PriorityTheme priorityTheme = new PriorityTheme();

        priorityTheme.setId( model.getId() );
        priorityTheme.setName( model.getName() );
        priorityTheme.setPriority( model.getPriority() );

        return priorityTheme;
    }

    @Override
    public PriorityThemeModel totoPriorityThemeModel(PriorityTheme priorityTheme) {
        if ( priorityTheme == null ) {
            return null;
        }

        PriorityThemeModel priorityThemeModel = new PriorityThemeModel();

        priorityThemeModel.setId( priorityTheme.getId() );
        priorityThemeModel.setName( priorityTheme.getName() );
        priorityThemeModel.setPriority( priorityTheme.getPriority() );

        return priorityThemeModel;
    }
}
