package ec.diners.com.application.commands.user.login;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.interfaces.user.IUserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Transactional
public class LoginUserCommandHandler implements Command.Handler<LoginUserCommand, Response<UserDto>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public LoginUserCommandHandler(Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<UserDto> handle(LoginUserCommand loginUserCommand) {
        var processResponse = new ProcessResponse<UserDto>();

        if (loginUserCommand.getEmail() == null || loginUserCommand.getEmail().isEmpty() ||
                loginUserCommand.getPwd() == null || loginUserCommand.getPwd().isEmpty()) {
            return processResponse.error("Email and password must not be null or empty");
        }

        Optional<UserDto> user = repository.findByEmailAndPwd(loginUserCommand.getEmail(), loginUserCommand.getPwd());
        if (user == null) {
            return processResponse.error("Invalid email or password");
        }

        return processResponse.success(user.get());
    }
}
