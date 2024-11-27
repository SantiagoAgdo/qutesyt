package ec.diners.com.infrastructure.repositories.user.userBase;


import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.user.base.UserRepository;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.user.base.UserDbModel;
import ec.diners.com.infrastructure.repositories.BaseRepositoryImpl;
import ec.diners.com.infrastructure.specification.JpaSpecificationBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserDbModel> implements UserRepository {

    private final JpaUserRepository repository;
    private final ModelMapper<User, UserDbModel> modelMapper;
    private final JpaSpecificationBuilder<UserDbModel> specificationBuilder;

    public UserRepositoryImpl(JpaUserRepository repository,
                              JpaSpecificationBuilder<UserDbModel> specificationBuilder,
                              UserModelMapperImpl modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public User add(User entity) {
        return add(entity, repository, modelMapper);
    }

    @Override
    public void delete(UUID id) {
        delete(id, repository);
    }

    @Override
    public void delete(User entity) {
        delete(entity, repository, modelMapper);
    }

    @Override
    public void deleteAll(List<User> entities) {
        deleteAll(entities, repository, modelMapper);
    }

    @Override
    public User update(User entity) {
        return update(entity, repository, modelMapper);
    }

    @Override
    public void updateAll(List<User> entities) {
        updateAll(entities, repository, modelMapper);
    }

    @Override
    public User getById(UUID id) {
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
    public GetEntitiesResponse<User> getAll(Criteria criteria) {
        return null;
    }

    @Override
    public User getFirst(Criteria criteria) {
        return getFirst(criteria, repository, specificationBuilder, modelMapper);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var userResponse = repository.findByEmailAndIsActive(email, Boolean.TRUE);
        if (userResponse == null) {
            return Optional.empty();
        }
        var user = modelMapper.modelToEntity(userResponse);
        return Optional.of(user);
    }
}
