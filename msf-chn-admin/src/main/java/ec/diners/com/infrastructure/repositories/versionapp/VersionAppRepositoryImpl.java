package ec.diners.com.infrastructure.repositories.versionapp;

import ec.diners.com.domain.entities.versionapp.VersionApp;
import ec.diners.com.domain.interfaces.repositories.versionapp.VersionAppRepository;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.views.VersionView;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.versionapp.VersionAppDbModel;
import ec.diners.com.infrastructure.repositories.BaseRepositoryImpl;
import ec.diners.com.infrastructure.specification.JpaSpecificationBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class VersionAppRepositoryImpl extends BaseRepositoryImpl<VersionApp, VersionAppDbModel> implements VersionAppRepository {

    private final JpaVersionAppRepository repository;
    private final ModelMapper<VersionApp, VersionAppDbModel> modelMapper;
    private final JpaSpecificationBuilder<VersionAppDbModel> specificationBuilder;

    public VersionAppRepositoryImpl(JpaVersionAppRepository repository,
                                    JpaSpecificationBuilder<VersionAppDbModel> specificationBuilder,
                                    VersionAppModelMapperImpl modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.specificationBuilder = specificationBuilder;
    }

    @Override
    public VersionApp add(VersionApp entity) {
        return add(entity, repository, modelMapper);
    }

    @Override
    public void delete(UUID id) {
        delete(id, repository);
    }

    @Override
    public void delete(VersionApp entity) {
        delete(entity, repository, modelMapper);
    }

    @Override
    public void deleteAll(List<VersionApp> entities) {
        deleteAll(entities, repository, modelMapper);
    }

    @Override
    public VersionApp update(VersionApp entity) {
        return update(entity, repository, modelMapper);
    }

    @Override
    public void updateAll(List<VersionApp> entities) {
        updateAll(entities, repository, modelMapper);
    }

    @Override
    public VersionApp getById(UUID id) {
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
    public GetEntitiesResponse<VersionApp> getAll(Criteria criteria) {
        return null;
    }

    @Override
    public VersionApp getFirst(Criteria criteria) {
        return getFirst(criteria, repository, specificationBuilder, modelMapper);
    }

    @Override
    public VersionApp findByIsActive(Boolean isActive) {
        var versionAppModel = repository.findByIsActive(Boolean.TRUE);
        return modelMapper.modelToEntity(versionAppModel);
    }

    @Override
    public VersionApp findVersionCurrentApp() {
        VersionApp currentVersion = null;
        var lastVersions
                = repository.findByIsActiveOrderByVersionNumberDescPublishedDateDesc(Boolean.TRUE);

        if (CollectionUtils.isNotEmpty(lastVersions)) {
            currentVersion = modelMapper.modelToEntity(lastVersions.get(0));
        }
        return currentVersion;
    }

    @Override
    public VersionView findHistoryVersionApp(String versionNumber) {

        return repository.findAllVersionHistoryView(versionNumber);
    }
}
