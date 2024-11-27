package ec.diners.com.application.utils;

import ec.diners.com.domain.models.FilterPaginationQueryModel;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.models.PaginationResponse;
import ec.diners.com.domain.specification.Criteria;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtils<T> {
    public GetEntitiesResponse<T> GetPagination(List<T> collection, FilterPaginationQueryModel filterPagination) {

        var criteria = new Criteria(
                filterPagination.constructFilters(),
                filterPagination.constructOrders(),
                filterPagination.getPage(),
                filterPagination.getPageSize()
        );

        var totalAmount = collection.size();
        if (totalAmount == 0)
        {
            return new GetEntitiesResponse<>(
                    new ArrayList<>(),
                    new PaginationResponse()
            );
        }

        if (totalAmount < criteria.getPageSize())
        {
            criteria.setPageSize(totalAmount);
        }

        if (criteria.getPage() != 0)
        {
            var rest = totalAmount % criteria.getPageSize() == 0 ? 0 : 1;
            var lastPage = totalAmount / criteria.getPageSize() + rest;
            if (criteria.getPage() >= lastPage)
            {
                return new GetEntitiesResponse<>(
                        new ArrayList<>(),
                        new PaginationResponse(criteria.getPage(), criteria.getPageSize(), totalAmount)
                );
            }
        }

        collection = collection
                .stream().skip((long) criteria.getPage() * criteria.getPageSize()).
                limit(criteria.getPageSize()).toList();

        return new GetEntitiesResponse<>(
                collection,
                new PaginationResponse(criteria.getPage(), criteria.getPageSize(), totalAmount)
        );
    }
}
