package ec.diners.com.application.commands.user.register;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record RegisterUserCommand(
        String email,
        String name,
        String lastname,
        String password
)
        implements Command<Response<RegisterUserCommandResponse>> {
}
