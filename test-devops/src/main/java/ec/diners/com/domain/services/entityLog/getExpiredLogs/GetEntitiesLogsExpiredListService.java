package ec.diners.com.domain.services.entityLog.getExpiredLogs;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.logs.EntityLog;
import ec.diners.com.domain.response.Response;

import java.util.List;


public record GetEntitiesLogsExpiredListService()
        implements Command<Response<List<EntityLog>>> {
}
