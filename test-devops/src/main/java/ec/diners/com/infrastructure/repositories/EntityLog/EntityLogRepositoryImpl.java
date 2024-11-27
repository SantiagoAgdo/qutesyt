package ec.diners.com.infrastructure.repositories.EntityLog;

import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.domain.interfaces.repositories.logs.EntityLogRepository;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.logs.EntityLogDbModel;
import ec.diners.com.infrastructure.repositories.BaseRepositoryImpl;
import ec.diners.com.infrastructure.specification.JpaSpecificationBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EntityLogRepositoryImpl extends BaseRepositoryImpl<EntityLog, EntityLogDbModel> implements EntityLogRepository {

    private final JpaEntityLogRepository repository;
    private final ModelMapper<EntityLog, EntityLogDbModel> modelMapper;
    private final JpaSpecificationBuilder<EntityLogDbModel> specificationBuilder;

    public EntityLogRepositoryImpl(JpaEntityLogRepository repository,
                                   JpaSpecificationBuilder<EntityLogDbModel> specificationBuilder,
                                   EntityLogModelMapperImpl modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public EntityLog add(EntityLog entity) {
        return add(entity, repository, modelMapper);
    }

    @Override
    public void delete(UUID id) {
        delete(id, repository);
    }

    @Override
    public void delete(EntityLog entity) {
        delete(entity, repository, modelMapper);
    }

    @Override
    public void deleteAll(List<EntityLog> entities) {
        deleteAll(entities, repository, modelMapper);
    }

    @Override
    public EntityLog update(EntityLog entity) {
        return update(entity, repository, modelMapper);
    }

    @Override
    public void updateAll(List<EntityLog> entities) {
        updateAll(entities, repository, modelMapper);
    }

    @Override
    public EntityLog getById(UUID id) {
        return getById(id, repository, modelMapper);
    }

    @Override
    public long count() {
        return count(repository);
    }

    @Override
    public long count(Criteria criteria) {
        return count(criteria, repository, specificationBuilder);
    }

    @Override
    public GetEntitiesResponse<EntityLog> getAll(Criteria criteria) {
        return getAll(criteria, repository, specificationBuilder, modelMapper);
    }

    @Override
    public EntityLog getFirst(Criteria criteria) {
        return getFirst(criteria, repository, specificationBuilder, modelMapper);
    }
}
