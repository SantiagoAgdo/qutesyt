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
public class UpdatePasswordCommandHandler implements Command.Handler<UpdatePasswordCommand, Response<Void>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public UpdatePasswordCommandHandler(Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<Void> handle(UpdatePasswordCommand updatePasswordCommand) {
        var processResponse = new ProcessResponse<Void>();

        if (updatePasswordCommand.getEmail() == null || updatePasswordCommand.getEmail().isEmpty() ||
                updatePasswordCommand.getOldPassword() == null || updatePasswordCommand.getOldPassword().isEmpty() ||
                updatePasswordCommand.getNewPassword() == null || updatePasswordCommand.getNewPassword().isEmpty()) {
            return processResponse.error("Email, old password, and new password must not be null or empty");
        }

        UserDto user = repository.findByEmailAndPwd(updatePasswordCommand.getEmail(), updatePasswordCommand.getOldPassword())
                .orElseThrow(() -> new EntityNotFoundException("Invalid email or old password"));

        user.setPwd(updatePasswordCommand.getNewPassword());
        repository.update(user);

        return processResponse.success(null);
    }
}
