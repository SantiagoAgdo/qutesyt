package ec.diners.com.application.commands.user.get;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.interfaces.user.IUserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class GetUserCommandHandler implements Command.Handler<GetUserCommand, Response<UserDto>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public GetUserCommandHandler(Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<UserDto> handle(GetUserCommand getUserCommand) {
        var processResponse = new ProcessResponse<UserDto>();

        if (getUserCommand.getUserId() == null || getUserCommand.getUserId() < 0) {
            return processResponse.error("User ID must not be null or empty");
        }

        UserDto user = repository.findById(getUserCommand.getUserId());
        if (user == null) {
            throw new EntityNotFoundException("User not found with ID: " + getUserCommand.getUserId());
        }

        return processResponse.success(user);
    }
}
