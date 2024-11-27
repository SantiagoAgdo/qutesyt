package ec.diners.com.application.queries.user.getById;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

import java.util.UUID;

public record GetUserByIdQuery(
        UUID userId
)
        implements Command<Response<GetUserByIdQueryResponseModel>> {

}
