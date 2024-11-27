package ec.diners.com.domain.interfaces.models;

import java.util.ArrayList;
import java.util.List;

public abstract class QueryResponseModel<T, I> {
    protected abstract T fromEntity(I entity);

    public List<T> fromEntities(List<I> entities){
        var responseList = new ArrayList<T>();
        for (var entity : entities){
            responseList.add(fromEntity(entity));
        }

        return responseList;
    }
}
