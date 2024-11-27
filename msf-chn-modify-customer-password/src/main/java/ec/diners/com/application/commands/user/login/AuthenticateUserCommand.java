package ec.diners.com.application.commands.user.login;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record AuthenticateUserCommand(
        String email,
        String password
)
        implements Command<Response<AuthenticateUserCommandResponse>> {
}
