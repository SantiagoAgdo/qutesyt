package ec.diners.com.application.commands.user.login;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.user.LoginUserRequest;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.response.Response;

public class LoginUserCommand extends LoginUserRequest implements Command<Response<UserDto>> {

    public LoginUserCommand() {
        super();
    }
}