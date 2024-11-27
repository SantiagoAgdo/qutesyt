package ec.diners.com.domain.interfaces.repositories;

import ec.diners.com.domain.entities.BaseEntity;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.specification.Criteria;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<TEntity extends BaseEntity> {
    TEntity add(TEntity entity);
    void delete(UUID id);
    void delete(TEntity entity);
    void deleteAll(List<TEntity> entities);
    TEntity update(TEntity entity);
    void updateAll(List<TEntity> entity);
    TEntity getById(UUID id);
    long count();
    long count(Criteria criteria);
    GetEntitiesResponse<TEntity> getAll(Criteria criteria);
    TEntity getFirst(Criteria criteria);
}
