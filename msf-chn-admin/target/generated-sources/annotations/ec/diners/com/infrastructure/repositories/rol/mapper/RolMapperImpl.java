package ec.diners.com.infrastructure.repositories.rol.mapper;

import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.infrastructure.modelsDb.rol.RoleModel;
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
public class RolMapperImpl implements RolMapper {

    @Override
    public RoleDto toRole(RoleModel model) {
        if ( model == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        if ( model.getId() != null ) {
            roleDto.setRoleId( String.valueOf( model.getId() ) );
        }
        roleDto.setDescription( model.getDescription() );
        if ( model.getEnabled() != null ) {
            roleDto.setEnabled( model.getEnabled() );
        }
        roleDto.setName( model.getName() );

        return roleDto;
    }

    @Override
    public List<RoleDto> toRoles(List<RoleModel> roleModels) {
        if ( roleModels == null ) {
            return null;
        }

        List<RoleDto> list = new ArrayList<RoleDto>( roleModels.size() );
        for ( RoleModel roleModel : roleModels ) {
            list.add( toRole( roleModel ) );
        }

        return list;
    }

    @Override
    public RoleModel toRoleModel(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        RoleModel.RoleModelBuilder roleModel = RoleModel.builder();

        if ( roleDto.getRoleId() != null ) {
            roleModel.id( Long.parseLong( roleDto.getRoleId() ) );
        }
        roleModel.description( roleDto.getDescription() );
        roleModel.enabled( roleDto.isEnabled() );
        roleModel.name( roleDto.getName() );

        return roleModel.build();
    }
}
