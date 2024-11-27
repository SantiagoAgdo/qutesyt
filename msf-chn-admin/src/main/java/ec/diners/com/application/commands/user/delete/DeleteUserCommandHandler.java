package ec.diners.com.application.commands.user.delete;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import ec.diners.com.domain.entities.user.UserDto;
import ec.diners.com.domain.interfaces.user.IUserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.persistence.EntityNotFoundException;

@Component
@Transactional
public class DeleteUserCommandHandler implements Command.Handler<DeleteUserCommand, Response<String>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public DeleteUserCommandHandler(Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<String> handle(DeleteUserCommand deleteUserCommand) {
        var processResponse = new ProcessResponse<String>();

        if (deleteUserCommand.getId() == null || deleteUserCommand.getId() < 0) {
            return processResponse.error("User ID must not be null or empty");
        }

        repository.delete(deleteUserCommand.getId());

        return processResponse.success(null);
    }
}
