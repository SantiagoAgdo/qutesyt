package ec.diners.com.infrastructure.interfaces;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ModelMapper<TEntity, TModel> {
    TEntity modelToEntity(TModel model);
    List<TEntity> modelsToEntities(List<TModel> models);
    TModel entityToModel(TEntity entity);
    List<TModel> entitiesToModels(List<TEntity> entities);
}
