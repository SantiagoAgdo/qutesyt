package ec.diners.com.application.commands.user.create;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.user.CreateUserRequest;
import ec.diners.com.application.dtos.response.user.CreateUserResponse;
import ec.diners.com.domain.response.Response;

public class CreateUserCommand extends CreateUserRequest implements Command<Response<CreateUserResponse>> {

    public CreateUserCommand() {
        super();
    }
}
