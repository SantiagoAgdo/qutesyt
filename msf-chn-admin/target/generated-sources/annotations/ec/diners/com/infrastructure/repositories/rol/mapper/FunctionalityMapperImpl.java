package ec.diners.com.infrastructure.repositories.rol.mapper;

import ec.diners.com.domain.entities.rol.FunctionalityDto;
import ec.diners.com.infrastructure.modelsDb.rol.Functionality;
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
public class FunctionalityMapperImpl implements FunctionalityMapper {

    @Override
    public FunctionalityDto toFunctionality(Functionality model) {
        if ( model == null ) {
            return null;
        }

        FunctionalityDto functionalityDto = new FunctionalityDto();

        functionalityDto.setId( model.getId() );
        functionalityDto.setCreatedAt( model.getCreatedAt() );
        functionalityDto.setDescription( model.getDescription() );
        functionalityDto.setName( model.getName() );
        functionalityDto.setUpdatedAt( model.getUpdatedAt() );

        return functionalityDto;
    }

    @Override
    public List<FunctionalityDto> toFunctionalitys(List<Functionality> functionalities) {
        if ( functionalities == null ) {
            return null;
        }

        List<FunctionalityDto> list = new ArrayList<FunctionalityDto>( functionalities.size() );
        for ( Functionality functionality : functionalities ) {
            list.add( toFunctionality( functionality ) );
        }

        return list;
    }

    @Override
    public Functionality toFunctionalityModel(FunctionalityDto functionalityDto) {
        if ( functionalityDto == null ) {
            return null;
        }

        Functionality.FunctionalityBuilder functionality = Functionality.builder();

        functionality.id( functionalityDto.getId() );
        functionality.createdAt( functionalityDto.getCreatedAt() );
        functionality.description( functionalityDto.getDescription() );
        functionality.name( functionalityDto.getName() );
        functionality.updatedAt( functionalityDto.getUpdatedAt() );

        return functionality.build();
    }
}
