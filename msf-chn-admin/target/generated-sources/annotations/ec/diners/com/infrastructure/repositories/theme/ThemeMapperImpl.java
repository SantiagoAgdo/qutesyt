package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.infrastructure.modelsDb.theme.ThemeModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ThemeMapperImpl implements ThemeMapper {

    @Override
    public Theme toTheme(ThemeModel model) {
        if ( model == null ) {
            return null;
        }

        Theme theme = new Theme();

        theme.setId( model.getId() );
        theme.setUuid( model.getUuid() );
        theme.setName( model.getName() );
        theme.setDescription( model.getDescription() );
        theme.setUserCreate( model.getCreatorUserId() );
        theme.setUserUpdate( model.getUpdaterUserId() );
        theme.setUpdatedAt( model.getUpdatedAt() );
        theme.setCreateAt( model.getCreatedAt() );
        theme.setIsActive( model.getIsActive() );

        return theme;
    }

    @Override
    public ThemeModel toThemeModel(Theme theme) {
        if ( theme == null ) {
            return null;
        }

        ThemeModel themeModel = new ThemeModel();

        themeModel.setId( theme.getId() );
        themeModel.setUuid( theme.getUuid() );
        themeModel.setName( theme.getName() );
        themeModel.setDescription( theme.getDescription() );
        themeModel.setCreatorUserId( theme.getUserCreate() );
        themeModel.setUpdaterUserId( theme.getUserUpdate() );
        themeModel.setUpdatedAt( theme.getUpdatedAt() );
        themeModel.setCreatedAt( theme.getCreateAt() );
        themeModel.setIsActive( theme.getIsActive() );

        return themeModel;
    }
}
