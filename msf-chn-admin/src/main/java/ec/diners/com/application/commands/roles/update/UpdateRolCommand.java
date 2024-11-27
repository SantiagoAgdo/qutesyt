package ec.diners.com.application.commands.roles.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.response.Response;

public class UpdateRolCommand implements Command<Response<RoleDto>> {

    private final RoleDto roleDto;

    public UpdateRolCommand(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }
}
