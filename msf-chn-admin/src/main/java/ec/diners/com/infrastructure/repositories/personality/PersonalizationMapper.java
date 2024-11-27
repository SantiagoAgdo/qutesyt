package ec.diners.com.infrastructure.repositories.personality;

import ec.diners.com.domain.entities.personality.PersonalizationDto;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonalizationMapper {
    @Mapping(source="id", target="id")
    @Mapping(source="dinersId", target="dinersId")
    @Mapping(source="themeId", target="themeId")
    @Mapping(source="bannerIds", target="bannerIds")
    @Mapping(source="moduleYourExperiencesIds", target="moduleYourExperiencesIds")
    @Mapping(source="moduleConfig", target="moduleConfig")
    @Mapping(source="recommendedByRestaurant", target="recommendedByRestaurant")
    @Mapping(source="recommendedByPromotions", target="recommendedByPromotions")
    @Mapping(source="recommendedByFashions", target="recommendedByFashions")
    @Mapping(source="isModeChallenger", target="isModeChallenger")
    @Mapping(source="registrationDate", target="registrationDate")
    PersonalizationDto toPersonalizationDto(PersonalizationModel model);

    List<PersonalizationDto> toEntities(List<PersonalizationModel> models);

    @InheritInverseConfiguration
    PersonalizationModel toPersonalizationHomeModel(PersonalizationDto personalizationDto);

}
