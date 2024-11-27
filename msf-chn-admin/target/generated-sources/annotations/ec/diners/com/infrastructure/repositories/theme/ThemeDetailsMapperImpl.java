package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.ThemeDetails;
import ec.diners.com.infrastructure.modelsDb.theme.ThemeDetailModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ThemeDetailsMapperImpl implements ThemeDetailsMapper {

    @Override
    public ThemeDetails toThemeDetails(ThemeDetailModel themeDetailModel) {
        if ( themeDetailModel == null ) {
            return null;
        }

        ThemeDetails themeDetails = new ThemeDetails();

        themeDetails.setId( themeDetailModel.getId() );
        themeDetails.setUuid( themeDetailModel.getUuid() );
        themeDetails.setName( themeDetailModel.getName() );
        themeDetails.setValue( themeDetailModel.getValue() );
        themeDetails.setThemeId( themeDetailModel.getThemeId() );

        return themeDetails;
    }

    @Override
    public ThemeDetailModel toThemeDetailsModel(ThemeDetails themeDetails) {
        if ( themeDetails == null ) {
            return null;
        }

        ThemeDetailModel themeDetailModel = new ThemeDetailModel();

        themeDetailModel.setId( themeDetails.getId() );
        themeDetailModel.setUuid( themeDetails.getUuid() );
        themeDetailModel.setName( themeDetails.getName() );
        themeDetailModel.setValue( themeDetails.getValue() );
        themeDetailModel.setThemeId( themeDetails.getThemeId() );

        return themeDetailModel;
    }
}
