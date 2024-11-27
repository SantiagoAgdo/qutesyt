package ec.diners.com.infrastructure.repositories.rol.impl;


import ec.diners.com.domain.entities.rol.RoleFuncionalityDto;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepositoryFuncionality;
import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionality;
import ec.diners.com.infrastructure.repositories.rol.contract.JpaRolFuncionalityRepository;
import ec.diners.com.infrastructure.repositories.rol.mapper.RolFuncionalityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RolFuncionalityRepository implements IRolRepositoryFuncionality {

    private final JpaRolFuncionalityRepository repository;

    private final RolFuncionalityMapper mapper;

    public RolFuncionalityRepository(JpaRolFuncionalityRepository repository, RolFuncionalityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

// Ver si falta
    @Override
    public String save(RoleFuncionalityDto roleFuncionalityDto) {
        RoleFunctionality roleFunctionality = mapper.toRoleFuncionalityModel(roleFuncionalityDto);
        RoleFunctionality functionalityRoleSave = repository.save(roleFunctionality);
        return functionalityRoleSave.getId().toString();
    }
}
