package ec.diners.com.application.queries.entityLog.getAll;

import an.awesome.pipelinr.Command;
import de.mobiuscode.nameof.Name;
import ec.diners.com.domain.constants.GlobalConstant;
import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.domain.interfaces.repositories.logs.EntityLogRepository;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.models.QueryEntitiesResponse;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterOperator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetEntityLogsQueryHandler implements Command.Handler<GetEntityLogsQuery, Response<GetEntitiesResponse<GetEntityLogsQueryResponseModel>>> {
    private final EntityLogRepository repository;

    public GetEntityLogsQueryHandler(EntityLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<GetEntitiesResponse<GetEntityLogsQueryResponseModel>> handle(GetEntityLogsQuery query) {

        var processResponse = new ProcessResponse<GetEntitiesResponse<GetEntityLogsQueryResponseModel>>();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(EntityLog.class, EntityLog::getCreatedBy), FilterOperator.EQ, GlobalConstant.MEMBER));
        query.filterPagination().addCustomFilters(filters);
        var criteria = new Criteria(query.filterPagination());

        var entitiesResponse = repository.getAll(criteria);


        var queryEntitiesResponse = new QueryEntitiesResponse<EntityLog, GetEntityLogsQueryResponseModel>();
        var response = queryEntitiesResponse.create(entitiesResponse, new GetEntityLogsQueryResponseModel());
        return processResponse.success(response);
    }
}
