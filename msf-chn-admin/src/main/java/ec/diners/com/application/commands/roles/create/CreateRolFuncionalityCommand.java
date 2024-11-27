package ec.diners.com.application.commands.roles.create;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.roles.CreateRolFuncionalityRequest;
import ec.diners.com.application.dtos.response.roles.CreateRolFuncionalityResponse;
import ec.diners.com.domain.response.Response;

public class CreateRolFuncionalityCommand extends CreateRolFuncionalityRequest implements Command<Response<CreateRolFuncionalityResponse>> {

    public CreateRolFuncionalityCommand() {
        super();
    }
}
