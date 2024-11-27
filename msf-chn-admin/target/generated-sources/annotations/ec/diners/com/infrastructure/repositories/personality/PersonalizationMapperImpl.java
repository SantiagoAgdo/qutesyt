package ec.diners.com.infrastructure.repositories.personality;

import ec.diners.com.domain.entities.personality.PersonalizationDto;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class PersonalizationMapperImpl implements PersonalizationMapper {

    @Override
    public PersonalizationDto toPersonalizationDto(PersonalizationModel model) {
        if ( model == null ) {
            return null;
        }

        PersonalizationDto personalizationDto = new PersonalizationDto();

        personalizationDto.setId( model.getId() );
        personalizationDto.setDinersId( model.getDinersId() );
        personalizationDto.setThemeId( model.getThemeId() );
        personalizationDto.setBannerIds( model.getBannerIds() );
        personalizationDto.setModuleYourExperiencesIds( model.getModuleYourExperiencesIds() );
        personalizationDto.setModuleConfig( model.getModuleConfig() );
        personalizationDto.setRecommendedByRestaurant( model.getRecommendedByRestaurant() );
        personalizationDto.setRecommendedByPromotions( model.getRecommendedByPromotions() );
        personalizationDto.setRecommendedByFashions( model.getRecommendedByFashions() );
        personalizationDto.setIsModeChallenger( model.getIsModeChallenger() );
        personalizationDto.setRegistrationDate( model.getRegistrationDate() );

        return personalizationDto;
    }

    @Override
    public List<PersonalizationDto> toEntities(List<PersonalizationModel> models) {
        if ( models == null ) {
            return null;
        }

        List<PersonalizationDto> list = new ArrayList<PersonalizationDto>( models.size() );
        for ( PersonalizationModel personalizationModel : models ) {
            list.add( toPersonalizationDto( personalizationModel ) );
        }

        return list;
    }

    @Override
    public PersonalizationModel toPersonalizationHomeModel(PersonalizationDto personalizationDto) {
        if ( personalizationDto == null ) {
            return null;
        }

        PersonalizationModel personalizationModel = new PersonalizationModel();

        personalizationModel.setId( personalizationDto.getId() );
        personalizationModel.setDinersId( personalizationDto.getDinersId() );
        personalizationModel.setThemeId( personalizationDto.getThemeId() );
        personalizationModel.setBannerIds( personalizationDto.getBannerIds() );
        personalizationModel.setModuleYourExperiencesIds( personalizationDto.getModuleYourExperiencesIds() );
        personalizationModel.setModuleConfig( personalizationDto.getModuleConfig() );
        personalizationModel.setRecommendedByRestaurant( personalizationDto.getRecommendedByRestaurant() );
        personalizationModel.setRecommendedByPromotions( personalizationDto.getRecommendedByPromotions() );
        personalizationModel.setRecommendedByFashions( personalizationDto.getRecommendedByFashions() );
        personalizationModel.setIsModeChallenger( personalizationDto.getIsModeChallenger() );
        personalizationModel.setRegistrationDate( personalizationDto.getRegistrationDate() );

        return personalizationModel;
    }
}
