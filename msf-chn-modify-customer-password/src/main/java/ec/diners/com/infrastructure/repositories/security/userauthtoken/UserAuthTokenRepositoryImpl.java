package ec.diners.com.infrastructure.repositories.security.userauthtoken;


import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.domain.interfaces.repositories.security.UserAuthTokenRepository;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.security.UserAuthTokenDbModel;
import ec.diners.com.infrastructure.repositories.BaseRepositoryImpl;
import ec.diners.com.infrastructure.specification.JpaSpecificationBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserAuthTokenRepositoryImpl extends BaseRepositoryImpl<UserAuthToken, UserAuthTokenDbModel> implements UserAuthTokenRepository {

    private final JpaUserAuthTokenRepository repository;
    private final ModelMapper<UserAuthToken, UserAuthTokenDbModel> modelMapper;
    private final JpaSpecificationBuilder<UserAuthTokenDbModel> specificationBuilder;

    public UserAuthTokenRepositoryImpl(JpaUserAuthTokenRepository repository,
                                       JpaSpecificationBuilder<UserAuthTokenDbModel> specificationBuilder,
                                       UserAuthTokenModelMapperImpl modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public UserAuthToken add(UserAuthToken entity) {
        return add(entity, repository, modelMapper);
    }

    @Override
    public void delete(UUID id) {
        delete(id, repository);
    }

    @Override
    public void delete(UserAuthToken entity) {
        delete(entity, repository, modelMapper);
    }

    @Override
    public void deleteAll(List<UserAuthToken> entities) {
        deleteAll(entities, repository, modelMapper);
    }

    @Override
    public UserAuthToken update(UserAuthToken entity) {
        return update(entity, repository, modelMapper);
    }

    @Override
    public void updateAll(List<UserAuthToken> entities) {
        updateAll(entities, repository, modelMapper);
    }

    @Override
    public UserAuthToken getById(UUID id) {
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
    public GetEntitiesResponse<UserAuthToken> getAll(Criteria criteria) {
        return getAll(criteria, repository, specificationBuilder, modelMapper);
    }

    @Override
    public UserAuthToken getFirst(Criteria criteria) {
        return getFirst(criteria, repository, specificationBuilder, modelMapper);
    }
}
