package ec.diners.com.infrastructure.repositories;

import ec.diners.com.domain.entities.BaseEntity;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.models.PaginationResponse;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.OrderBy;
import ec.diners.com.domain.specification.OrderType;
import ec.diners.com.infrastructure.interfaces.ModelMapper;
import ec.diners.com.infrastructure.modelsDb.BaseDbModel;
import ec.diners.com.infrastructure.specification.JpaSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public abstract class BaseRepositoryImpl<TEntity extends BaseEntity, TModel extends BaseDbModel> {

    protected TEntity add(TEntity entity, JpaRepository<TModel, UUID> repository, ModelMapper<TEntity, TModel> mapper) {
        if (entity.getId() == null || entity.getId().toString().isEmpty()) {
            entity.setId(UUID.randomUUID());
        }

        entity.setUpdateAt(new Date());

        var model = mapper.entityToModel(entity);

        var newModel = repository.save(model);
        return mapper.modelToEntity(newModel);
    }

    protected void delete(UUID id, JpaRepository<TModel, UUID> repository) {
        repository.deleteById(id);
    }

    protected void delete(TEntity entity, JpaRepository<TModel, UUID> repository, ModelMapper<TEntity, TModel> mapper) {
        var model = mapper.entityToModel(entity);
        repository.delete(model);
    }

    protected void deleteAll(List<TEntity> entities, JpaRepository<TModel, UUID> repository, ModelMapper<TEntity, TModel> mapper) {
        var models = mapper.entitiesToModels(entities);
        repository.deleteAll(models);
    }

    protected TEntity update(TEntity entity, JpaRepository<TModel, UUID> repository, ModelMapper<TEntity, TModel> mapper) {
        entity.setUpdateAt(new Date());
        var model = mapper.entityToModel(entity);
        var newModel = repository.save(model);
        return mapper.modelToEntity(newModel);
    }

    protected void updateAll(List<TEntity> entities, JpaRepository<TModel, UUID> repository, ModelMapper<TEntity, TModel> mapper) {
        var models = new ArrayList<TModel>();
        for (TEntity entity : entities) {
            entity.setUpdateAt(new Date());
            var model = mapper.entityToModel(entity);
            models.add(model);
        }

        repository.saveAll(models);
    }

    protected TEntity getById(UUID id, JpaRepository<TModel, UUID> repository, ModelMapper<TEntity, TModel> mapper) {
        var model = repository.findById(id);
        return model.map(m -> mapper.modelToEntity(m)).orElse(null);
    }

    protected long count(JpaRepository<TModel, UUID> repository) {
        return repository.count();
    }

    protected long count(Criteria criteria,
                         JpaSpecificationExecutor<TModel> repository,
                         JpaSpecificationBuilder<TModel> specificationBuilder) {

        var specification = specificationBuilder.createSpecification(criteria);
        return repository.count(specification);
    }


    protected TEntity getFirst(Criteria criteria,
                               JpaSpecificationExecutor<TModel> repository,
                               JpaSpecificationBuilder<TModel> specificationBuilder,
                               ModelMapper<TEntity, TModel> mapper) {

        var specification = specificationBuilder.createSpecification(criteria);
        Page<TModel> models;
        if (criteria.getOrders().isEmpty()) {
            models = repository.findAll(specification, PageRequest.of(0, 1));
        } else {
            models = getModelsBySpecificationOrder(criteria.getOrders(), specification, repository, 0, 1);
        }

        if (models.isEmpty()) {
            return null;
        }
        var model = models.toList().get(0);
        return mapper.modelToEntity(model);
    }

    private GetEntitiesResponse<TEntity> toListOrderedPagedValues(Criteria criteria,
                                                                  JpaSpecificationExecutor<TModel> repository,
                                                                  JpaSpecificationBuilder<TModel> specificationBuilder,
                                                                  ModelMapper<TEntity, TModel> modelMapper) {

        var specification = specificationBuilder.createSpecification(criteria);

        //Get the total amount of entities
        var totalAmount = (int) repository.count(specification);

        //If there is no entities return empty list of entities.
        if (totalAmount == 0) {
            return new GetEntitiesResponse<>();
        }

        //valid max page size by total amount.
        if (totalAmount < criteria.getPageSize()) {
            criteria.setPageSize(totalAmount);
        }

        //Verify if page is correct. The first correct page is 0
        if (criteria.getPage() != 0) {
            var rest = totalAmount % criteria.getPageSize() == 0 ? 0 : 1;
            var lastPage = totalAmount / criteria.getPageSize() + rest;
            if (criteria.getPage() >= lastPage) {
                return new GetEntitiesResponse<>(
                        new PaginationResponse(criteria.getPage(), criteria.getPageSize(), totalAmount)
                );
            }
        }

        //Get entity list by criteria
        var pageModelResponse = createPaginationByCriteria(criteria, repository, specification);

        return new GetEntitiesResponse<>(
                modelMapper.modelsToEntities(pageModelResponse.toList()),
                new PaginationResponse(criteria.getPage(), criteria.getPageSize(), totalAmount)
        );
    }

    private Page<TModel> createPaginationByCriteria(Criteria criteria,
                                                    JpaSpecificationExecutor<TModel> repository,
                                                    Specification<TModel> specification) {
        var orders = criteria.getOrders();
        var page = criteria.getPage();
        var pageSize = criteria.getPageSize();

        if (orders.isEmpty()) {
            return repository.findAll(specification, PageRequest.of(page, pageSize));
        }

        return getModelsBySpecificationOrder(orders, specification, repository, page, pageSize);
    }

    private Page<TModel> getModelsBySpecificationOrder(List<OrderBy> orders,
                                                       Specification<TModel> specification,
                                                       JpaSpecificationExecutor<TModel> repository,
                                                       int page, int pageSize) {
        Page<TModel> pageModelResponse;
        List<String> orderAscendingProperties = new ArrayList<>();
        List<String> orderDescendingProperties = new ArrayList<>();
        orders.forEach(order -> {
            if (order.getOrderType() == OrderType.ASC) {
                orderAscendingProperties.add(order.getFieldName());
            } else {
                orderDescendingProperties.add(order.getFieldName());
            }
        });

        if (!orderAscendingProperties.isEmpty() && !orderDescendingProperties.isEmpty()) {
            pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize,
                    Sort.by(Sort.Direction.ASC, orderAscendingProperties.toArray(new String[orderAscendingProperties.size()])).and(
                            Sort.by(Sort.Direction.DESC, orderDescendingProperties.toArray(new String[orderDescendingProperties.size()]))
                    )));
        } else if (!orderAscendingProperties.isEmpty()) {
            pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize,
                    Sort.by(Sort.Direction.ASC, orderAscendingProperties.toArray(new String[orderAscendingProperties.size()]))));
        } else {
            pageModelResponse = repository.findAll(specification, PageRequest.of(page, pageSize,
                    Sort.by(Sort.Direction.DESC, orderDescendingProperties.toArray(new String[orderDescendingProperties.size()]))));
        }

        return pageModelResponse;
    }
}
