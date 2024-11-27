package ec.diners.com.application.queries.entityLog.getAll;

import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import org.springframework.stereotype.Component;

@Component
public class GetEntityLogsQueryValidator implements CommandValidator<GetEntityLogsQuery, Response<GetEntitiesResponse<GetEntityLogsQueryResponseModel>>> {
    @Override
    public void validate(GetEntityLogsQuery command) {
    }
}
