package ec.diners.com.application.commands.roles.create;


import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.dtos.response.roles.CreateRolFuncionalityResponse;
import ec.diners.com.domain.entities.rol.FunctionalityDto;
import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.entities.rol.RoleFuncionalityDto;
import ec.diners.com.domain.interfaces.repositories.rol.IFuncionalityRepository;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepository;
import ec.diners.com.domain.interfaces.repositories.rol.IRolRepositoryFuncionality;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionalityId;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Transactional
public class CreateRolFuncionalityCommandHandler implements Command.Handler<CreateRolFuncionalityCommand, Response<CreateRolFuncionalityResponse>> {

    private final IRolRepositoryFuncionality repository;
    private final IRolRepository repositoryRol;
    private final IFuncionalityRepository repositoryFuncionality;
    private final Pipeline mediator;

    public CreateRolFuncionalityCommandHandler(Pipeline mediator, IRolRepositoryFuncionality repository, IFuncionalityRepository repositoryFuncionality, IRolRepository repositoryRol) {
        this.mediator = mediator;
        this.repository = repository;
        this.repositoryRol = repositoryRol;
        this.repositoryFuncionality = repositoryFuncionality;
    }

    @Override
    public Response<CreateRolFuncionalityResponse> handle(CreateRolFuncionalityCommand rolFuncionalityCommand) {
        var processResponse = new ProcessResponse<CreateRolFuncionalityResponse>();

        if (rolFuncionalityCommand.getFuncionalityId() == null || rolFuncionalityCommand.getRoleId() == 0) {
            return processResponse.error("Field null or empty");
        }

        RoleFuncionalityDto roleFuncionalityDto = new RoleFuncionalityDto();
        roleFuncionalityDto.setId(new RoleFunctionalityId(rolFuncionalityCommand.getRoleId(), rolFuncionalityCommand.getFuncionalityId()));
        roleFuncionalityDto.setAssignedAt(new Date());

        FunctionalityDto functionality = repositoryFuncionality.findById(rolFuncionalityCommand.getFuncionalityId());
        RoleDto role = repositoryRol.findById(String.valueOf(rolFuncionalityCommand.getRoleId()));

        roleFuncionalityDto.setRole(role);
        roleFuncionalityDto.setFunctionality(functionality);

        String roleId = repository.save(roleFuncionalityDto);

        var response = new CreateRolFuncionalityResponse(roleId);
        return processResponse.success(response);
    }

}
