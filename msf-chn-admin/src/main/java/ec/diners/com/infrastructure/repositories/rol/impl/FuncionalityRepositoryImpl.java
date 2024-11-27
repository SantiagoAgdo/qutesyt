package ec.diners.com.infrastructure.repositories.rol.impl;

import ec.diners.com.domain.entities.rol.FunctionalityDto;
import ec.diners.com.domain.interfaces.repositories.rol.IFuncionalityRepository;
import ec.diners.com.infrastructure.repositories.rol.contract.JpaFuncionalityRepository;
import ec.diners.com.infrastructure.repositories.rol.mapper.FunctionalityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionalityRepositoryImpl implements IFuncionalityRepository {

    private final JpaFuncionalityRepository repository;

    private final FunctionalityMapper mapper;

    public FuncionalityRepositoryImpl(JpaFuncionalityRepository repository, FunctionalityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FunctionalityDto findById(Long id) {
        return repository.findById(id)
                .map(mapper::toFunctionality)
                .orElseThrow(() -> new EntityNotFoundException("Functionality not found with id: " + id));
    }
}
