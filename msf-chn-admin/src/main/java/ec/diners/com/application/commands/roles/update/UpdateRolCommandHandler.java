package ec.diners.com.application.commands.roles.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class UpdateRolCommandHandler implements Command.Handler<UpdateRolCommand, Response<RoleDto>> {

    private final IRolRepository repository;

    @Autowired
    public UpdateRolCommandHandler(IRolRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<RoleDto> handle(UpdateRolCommand updateRolCommand) {
        System.out.printf("Actualizando...");
        var processResponse = new ProcessResponse<RoleDto>();
        try {
            repository.update(updateRolCommand.getRoleDto());
            return processResponse.success(updateRolCommand.getRoleDto());
        } catch (EntityNotFoundException e) {
            return processResponse.error("Role not found with id:" + updateRolCommand.getRoleDto().getRoleId());
        }
    }

}
