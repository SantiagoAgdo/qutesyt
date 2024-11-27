package ec.diners.com.domain.interfaces.mapper;

import java.util.List;

public interface EntityMapper<TEntity, TModel> {
    TEntity toEntity(TModel model);
    List<TEntity> toEntities(List<TModel> models);
    TModel toModel(TEntity entity);
}
