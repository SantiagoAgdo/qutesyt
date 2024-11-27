package ec.diners.com.domain.services.entityLog.userLog;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record CreateEntityLogUserService(
        String action,
        String description)
        implements Command<Response<Boolean>> {
}
