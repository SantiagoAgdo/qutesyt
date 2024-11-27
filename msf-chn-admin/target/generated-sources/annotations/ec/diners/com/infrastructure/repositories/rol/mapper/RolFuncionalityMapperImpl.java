package ec.diners.com.infrastructure.repositories.rol.mapper;

import ec.diners.com.domain.entities.rol.RoleFuncionalityDto;
import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionality;
import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionalityId;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class RolFuncionalityMapperImpl implements RolFuncionalityMapper {

    @Autowired
    private RolMapper rolMapper;
    @Autowired
    private FunctionalityMapper functionalityMapper;

    @Override
    public RoleFuncionalityDto toRoleFuncionality(RoleFunctionality model) {
        if ( model == null ) {
            return null;
        }

        RoleFuncionalityDto roleFuncionalityDto = new RoleFuncionalityDto();

        roleFuncionalityDto.setId( roleFunctionalityIdToRoleFunctionalityId( model.getId() ) );
        roleFuncionalityDto.setRole( rolMapper.toRole( model.getRole() ) );
        roleFuncionalityDto.setFunctionality( functionalityMapper.toFunctionality( model.getFunctionality() ) );
        roleFuncionalityDto.setAssignedAt( model.getAssignedAt() );

        return roleFuncionalityDto;
    }

    @Override
    public List<RoleFuncionalityDto> toRolesFuncionality(List<RoleFunctionality> roleModels) {
        if ( roleModels == null ) {
            return null;
        }

        List<RoleFuncionalityDto> list = new ArrayList<RoleFuncionalityDto>( roleModels.size() );
        for ( RoleFunctionality roleFunctionality : roleModels ) {
            list.add( toRoleFuncionality( roleFunctionality ) );
        }

        return list;
    }

    @Override
    public RoleFunctionality toRoleFuncionalityModel(RoleFuncionalityDto roleFuncionalityDto) {
        if ( roleFuncionalityDto == null ) {
            return null;
        }

        RoleFunctionality.RoleFunctionalityBuilder roleFunctionality = RoleFunctionality.builder();

        roleFunctionality.id( roleFunctionalityIdToRoleFunctionalityId1( roleFuncionalityDto.getId() ) );
        roleFunctionality.role( rolMapper.toRoleModel( roleFuncionalityDto.getRole() ) );
        roleFunctionality.functionality( functionalityMapper.toFunctionalityModel( roleFuncionalityDto.getFunctionality() ) );
        roleFunctionality.assignedAt( roleFuncionalityDto.getAssignedAt() );

        return roleFunctionality.build();
    }

    protected RoleFunctionalityId roleFunctionalityIdToRoleFunctionalityId(RoleFunctionalityId roleFunctionalityId) {
        if ( roleFunctionalityId == null ) {
            return null;
        }

        RoleFunctionalityId roleFunctionalityId1 = new RoleFunctionalityId();

        roleFunctionalityId1.setRoleId( roleFunctionalityId.getRoleId() );
        roleFunctionalityId1.setFunctionalityId( roleFunctionalityId.getFunctionalityId() );

        return roleFunctionalityId1;
    }

    protected RoleFunctionalityId roleFunctionalityIdToRoleFunctionalityId1(RoleFunctionalityId roleFunctionalityId) {
        if ( roleFunctionalityId == null ) {
            return null;
        }

        RoleFunctionalityId roleFunctionalityId1 = new RoleFunctionalityId();

        roleFunctionalityId1.setRoleId( roleFunctionalityId.getRoleId() );
        roleFunctionalityId1.setFunctionalityId( roleFunctionalityId.getFunctionalityId() );

        return roleFunctionalityId1;
    }
}
