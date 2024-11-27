package ec.diners.com.infrastructure.repositories.partnerIntegration;

import ec.diners.com.application.dtos.response.partnerIntegration.PartnerIntegrationDto;
import ec.diners.com.infrastructure.modelsDb.partnerIntegration.PartnerIntegrationModel;
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
public class PartnerIntegrationMapperImpl implements PartnerIntegrationMapper {

    @Override
    public PartnerIntegrationDto promtoPartnerIntegrationDto(PartnerIntegrationModel model) {
        if ( model == null ) {
            return null;
        }

        PartnerIntegrationDto partnerIntegrationDto = new PartnerIntegrationDto();

        partnerIntegrationDto.setId( model.getId() );
        partnerIntegrationDto.setDinersId( model.getDinersId() );
        partnerIntegrationDto.setName( model.getName() );
        partnerIntegrationDto.setThemeId( model.getThemeId() );
        partnerIntegrationDto.setCardProducts( model.getCardProducts() );
        partnerIntegrationDto.setCodeSegment( model.getCodeSegment() );
        partnerIntegrationDto.setIsRegistered( model.getIsRegistered() );
        partnerIntegrationDto.setLatitude( model.getLatitude() );
        partnerIntegrationDto.setLongitude( model.getLongitude() );

        return partnerIntegrationDto;
    }

    @Override
    public List<PartnerIntegrationDto> toEntities(List<PartnerIntegrationModel> models) {
        if ( models == null ) {
            return null;
        }

        List<PartnerIntegrationDto> list = new ArrayList<PartnerIntegrationDto>( models.size() );
        for ( PartnerIntegrationModel partnerIntegrationModel : models ) {
            list.add( promtoPartnerIntegrationDto( partnerIntegrationModel ) );
        }

        return list;
    }

    @Override
    public PartnerIntegrationModel toPartnerIntegrationModel(PartnerIntegrationDto partnerIntegrationDto) {
        if ( partnerIntegrationDto == null ) {
            return null;
        }

        PartnerIntegrationModel partnerIntegrationModel = new PartnerIntegrationModel();

        partnerIntegrationModel.setId( partnerIntegrationDto.getId() );
        partnerIntegrationModel.setDinersId( partnerIntegrationDto.getDinersId() );
        partnerIntegrationModel.setName( partnerIntegrationDto.getName() );
        partnerIntegrationModel.setThemeId( partnerIntegrationDto.getThemeId() );
        partnerIntegrationModel.setCardProducts( partnerIntegrationDto.getCardProducts() );
        partnerIntegrationModel.setCodeSegment( partnerIntegrationDto.getCodeSegment() );
        partnerIntegrationModel.setIsRegistered( partnerIntegrationDto.getIsRegistered() );
        partnerIntegrationModel.setLatitude( partnerIntegrationDto.getLatitude() );
        partnerIntegrationModel.setLongitude( partnerIntegrationDto.getLongitude() );

        return partnerIntegrationModel;
    }
}
