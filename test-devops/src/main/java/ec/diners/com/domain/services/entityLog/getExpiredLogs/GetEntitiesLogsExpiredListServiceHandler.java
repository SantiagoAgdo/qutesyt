package ec.diners.com.domain.services.entityLog.getExpiredLogs;

import an.awesome.pipelinr.Command;
import de.mobiuscode.nameof.Name;
import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.domain.interfaces.repositories.logs.EntityLogRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterOperator;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class GetEntitiesLogsExpiredListServiceHandler implements Command.Handler<GetEntitiesLogsExpiredListService, Response<List<EntityLog>>>{

    private final EntityLogRepository repository;

    public GetEntitiesLogsExpiredListServiceHandler(EntityLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<List<EntityLog>> handle(GetEntitiesLogsExpiredListService service) {
        var processResponse = new ProcessResponse<List<EntityLog>>();

        var list = new ArrayList<EntityLog>();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(EntityLog.class, EntityLog::getCreateAt), FilterOperator.LT, DateUtils.addDays(new Date(), -90)));

        var criteria = new Criteria(filters, Integer.MAX_VALUE);

        var getResponse = repository.getAll(criteria);
        if (getResponse.getEntities() == null || getResponse.getEntities().isEmpty()) {
            return processResponse.success(list);
        }

        var presentEntity = getResponse.getEntities().stream().findFirst();
        if (presentEntity.isEmpty()) {
            return processResponse.error("Entity not found");
        }

        list = new ArrayList<>(getResponse.getEntities());
        return processResponse.success(list);
    }
}
