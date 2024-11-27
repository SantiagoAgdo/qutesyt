package ec.diners.com.application.commands.roles.create;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.roles.CreateRolRequest;
import ec.diners.com.application.dtos.response.roles.CreateRoleResponse;
import ec.diners.com.domain.response.Response;

public class CreateRolCommand extends CreateRolRequest implements Command<Response<CreateRoleResponse>> {

    public CreateRolCommand() {
        super();
    }
}
