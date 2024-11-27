package ec.diners.com.application.queries.versionapp;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.versionapp.VersionAppRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class GetCurrentVersionAppQueryHandler implements Command.Handler<GetCurrentVersionAppQuery, Response<GetCurrentVersionAppQueryResponseModel>> {
    private final VersionAppRepository versionAppRepository;

    public GetCurrentVersionAppQueryHandler(VersionAppRepository versionAppRepository) {
        this.versionAppRepository = versionAppRepository;
    }


    @Override
    public Response<GetCurrentVersionAppQueryResponseModel> handle(GetCurrentVersionAppQuery query) {

        var processResponse = new ProcessResponse<GetCurrentVersionAppQueryResponseModel>();

        var currentVersionApp = versionAppRepository.findVersionCurrentApp();

        var getEntitiesResponse = new GetCurrentVersionAppQueryResponseModel();

        var response = getEntitiesResponse.fromEntity(currentVersionApp);
        return processResponse.success(response);
    }
}
