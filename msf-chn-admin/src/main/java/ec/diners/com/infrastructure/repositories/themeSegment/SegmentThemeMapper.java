package ec.diners.com.infrastructure.repositories.themeSegment;

import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.infrastructure.modelsDb.theme.SegmentThemeModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SegmentThemeMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="segmentId", target="segmentId")
    @Mapping(source="themeSegmentId", target="themeSegmentId")
    @Mapping(source="themeCampaignId", target="themeCampaignId")
    @Mapping(source="createdAt", target="createdAt")
    @Mapping(source="creatorUserId", target="creatorUserId")
    @Mapping(source="isActive", target="isActive")
    @Mapping(source="updaterUserId", target="updaterUserId")
    SegmentTheme toSegmentTheme(SegmentThemeModel model);

    @InheritInverseConfiguration
    SegmentThemeModel toSegmentThemeModel(SegmentTheme segmentTheme);
}
