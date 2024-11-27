package ec.diners.com.application.commands.user.create;


import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.dtos.response.user.CreateUserResponse;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.interfaces.user.IUserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CreateUserCommandHandler implements Command.Handler<CreateUserCommand, Response<CreateUserResponse>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public CreateUserCommandHandler( Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<CreateUserResponse> handle(CreateUserCommand createUserCommand) {
        var processResponse = new ProcessResponse<CreateUserResponse>();

        if (createUserCommand.getName() == null || createUserCommand.getName().isEmpty()) {
            return processResponse.error("User name must not be null or empty");
        }

        UserDto user = new UserDto();
        user.setName(createUserCommand.getName());
        user.setLastName(createUserCommand.getLastName());
        user.setEmail(createUserCommand.getEmail());
        user.setPhoto(createUserCommand.getPhoto());
        user.setPwd(createUserCommand.getPwd());
        user.setIdentificationType(createUserCommand.getIdentificationType());
        user.setIdentificationNumber(createUserCommand.getIdentificationNumber());
        user.setTelephoneNumber(createUserCommand.getTelephoneNumber());
        user.setToken(createUserCommand.getToken());
        user.setEnabled(createUserCommand.isEnabled());

        String userId = repository.save(user);

        var response = new CreateUserResponse(userId);
        return processResponse.success(response);
    }
}
