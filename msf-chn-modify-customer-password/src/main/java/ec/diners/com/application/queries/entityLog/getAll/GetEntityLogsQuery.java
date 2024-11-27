package ec.diners.com.application.queries.entityLog.getAll;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.models.FilterPaginationQueryModel;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.response.Response;

public record GetEntityLogsQuery(
        FilterPaginationQueryModel filterPagination)
        implements Command<Response<GetEntitiesResponse<GetEntityLogsQueryResponseModel>>> {
}
