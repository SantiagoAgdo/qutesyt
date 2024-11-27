package ec.diners.com.domain.services.entityLog.systemLog;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record CreateEntityLogSystemService(
        String logType,
        String description)
        implements Command<Response<Boolean>> {
}
