package ec.diners.com.application.commands.roles.delete;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class DeleteRolCommandHandler implements Command.Handler<DeleteRolCommand, Response<String>> {

    private final IRolRepository repository;

    @Autowired
    public DeleteRolCommandHandler(IRolRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<String> handle(DeleteRolCommand deleteRolCommand) {
        System.out.printf("Eliminando...");
        var processResponse = new ProcessResponse<String>();
        try {
            repository.delete(deleteRolCommand.getRoleId());
            return processResponse.success("Eliminado");
        } catch (EntityNotFoundException e) {
            return processResponse.error("Role not found with id:" + deleteRolCommand.getRoleId());
        }
    }
}
