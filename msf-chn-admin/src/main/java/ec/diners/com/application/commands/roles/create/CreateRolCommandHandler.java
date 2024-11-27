package ec.diners.com.application.commands.roles.create;


import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.dtos.response.roles.CreateRoleResponse;
import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CreateRolCommandHandler implements Command.Handler<CreateRolCommand, Response<CreateRoleResponse>> {

    private final IRolRepository repository;
    private final Pipeline mediator;

    public CreateRolCommandHandler( Pipeline mediator, IRolRepository repository) {
        this.mediator = mediator;
        this.repository = repository;
    }

    @Override
    public Response<CreateRoleResponse> handle(CreateRolCommand createRolCommand) {
        var processResponse = new ProcessResponse<CreateRoleResponse>();

        if (createRolCommand.getName() == null || createRolCommand.getName().isEmpty()) {
            return processResponse.error("Role name must not be null or empty");
        }

        RoleDto roleDto = new RoleDto();
        roleDto.setName(createRolCommand.getName());
        roleDto.setDescription(createRolCommand.getDescription());
        roleDto.setEnabled(true);

        String roleId = repository.save(roleDto);

        var response = new CreateRoleResponse(roleId);
        return processResponse.success(response);
    }
}
