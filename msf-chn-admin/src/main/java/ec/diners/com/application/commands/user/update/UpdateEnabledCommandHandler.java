package ec.diners.com.application.commands.user.update;

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
public class UpdateEnabledCommandHandler implements Command.Handler<UpdateEnabledCommand, Response<Void>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public UpdateEnabledCommandHandler(Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<Void> handle(UpdateEnabledCommand updateEnabledCommand) {
        var processResponse = new ProcessResponse<Void>();

        if (updateEnabledCommand.getEmail() == null || updateEnabledCommand.getEmail().isEmpty()) {
            return processResponse.error("Email must not be null or empty");
        }

        UserDto user = repository.findByEmail(updateEnabledCommand.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + updateEnabledCommand.getEmail()));

        user.setEnabled(updateEnabledCommand.isEnabled());
        repository.update(user);

        return processResponse.success(null);
    }
}
