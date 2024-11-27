package ec.diners.com.infrastructure.repositories.themeSegment;

import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.infrastructure.modelsDb.theme.SegmentThemeModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class SegmentThemeMapperImpl implements SegmentThemeMapper {

    @Override
    public SegmentTheme toSegmentTheme(SegmentThemeModel model) {
        if ( model == null ) {
            return null;
        }

        SegmentTheme segmentTheme = new SegmentTheme();

        segmentTheme.setId( model.getId() );
        segmentTheme.setSegmentId( model.getSegmentId() );
        segmentTheme.setThemeSegmentId( model.getThemeSegmentId() );
        segmentTheme.setThemeCampaignId( model.getThemeCampaignId() );
        segmentTheme.setCreatedAt( model.getCreatedAt() );
        segmentTheme.setCreatorUserId( model.getCreatorUserId() );
        segmentTheme.setIsActive( model.getIsActive() );
        segmentTheme.setUpdaterUserId( model.getUpdaterUserId() );

        return segmentTheme;
    }

    @Override
    public SegmentThemeModel toSegmentThemeModel(SegmentTheme segmentTheme) {
        if ( segmentTheme == null ) {
            return null;
        }

        SegmentThemeModel segmentThemeModel = new SegmentThemeModel();

        segmentThemeModel.setId( segmentTheme.getId() );
        segmentThemeModel.setSegmentId( segmentTheme.getSegmentId() );
        segmentThemeModel.setThemeSegmentId( segmentTheme.getThemeSegmentId() );
        segmentThemeModel.setThemeCampaignId( segmentTheme.getThemeCampaignId() );
        segmentThemeModel.setCreatedAt( segmentTheme.getCreatedAt() );
        segmentThemeModel.setCreatorUserId( segmentTheme.getCreatorUserId() );
        segmentThemeModel.setIsActive( segmentTheme.getIsActive() );
        segmentThemeModel.setUpdaterUserId( segmentTheme.getUpdaterUserId() );

        return segmentThemeModel;
    }
}
