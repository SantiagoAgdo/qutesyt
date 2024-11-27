package ec.diners.com.domain.models;

import ec.diners.com.domain.entities.BaseEntity;
import ec.diners.com.domain.interfaces.models.QueryResponseModel;

public class QueryEntitiesResponse<I extends BaseEntity, R extends QueryResponseModel<R, I>> {

    public GetEntitiesResponse<R> create(GetEntitiesResponse<I> entitiesResponse, R queryResponseModel) {
        var entities = entitiesResponse.getEntities();
        var responseList = queryResponseModel.fromEntities(entities);

        return new GetEntitiesResponse<>(responseList, entitiesResponse.getPagination());
    }
}
