package ec.diners.com.application.queries.versionapp;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record GetCurrentVersionAppQuery(

        )
        implements Command<Response<GetCurrentVersionAppQueryResponseModel>> {
}
