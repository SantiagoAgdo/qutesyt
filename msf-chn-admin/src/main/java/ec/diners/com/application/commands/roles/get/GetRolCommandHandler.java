package ec.diners.com.application.commands.roles.get;


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
public class GetRolCommandHandler implements Command.Handler<GetRolCommand, Response<RoleDto>> {

    private final IRolRepository repository;

    @Autowired
    public GetRolCommandHandler(IRolRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<RoleDto> handle(GetRolCommand getRolCommand) {
        var processResponse = new ProcessResponse<RoleDto>();
        try {
            RoleDto roleDto = repository.findById(getRolCommand.getRoleId());
            return processResponse.success(roleDto);
        } catch (EntityNotFoundException e) {
            return processResponse.error("Role not found with id:" + getRolCommand.getRoleId());
        }
    }
}
