package ec.diners.com.application.commands.user.get;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.dtos.response.user.RecoverUserResponse;
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
public class RecoverPasswordCommandHandler implements Command.Handler<RecoverPasswordCommand, Response<RecoverUserResponse>> {

    private final IUserRepository repository;
    private final Pipeline mediator;

    public RecoverPasswordCommandHandler(Pipeline mediator, IUserRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<RecoverUserResponse> handle(RecoverPasswordCommand recoverPasswordCommand) {
        var processResponse = new ProcessResponse<RecoverUserResponse>();

        if (recoverPasswordCommand.getEmail() == null || recoverPasswordCommand.getEmail().isEmpty()) {
            return processResponse.error("Email must not be null or empty");
        }

        Optional<UserDto> user = repository.findByEmail(recoverPasswordCommand.getEmail());
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found with email: " + recoverPasswordCommand.getEmail());
        }

        //Logica por definir
        System.out.println("Password recovery requested for email: " + recoverPasswordCommand.getEmail());

        return processResponse.success(new RecoverUserResponse("pepeito@gmail.com", "123456"));
    }
}