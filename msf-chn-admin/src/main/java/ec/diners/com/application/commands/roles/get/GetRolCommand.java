package ec.diners.com.application.commands.roles.get;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.rol.RoleDto;
import ec.diners.com.domain.response.Response;

public class GetRolCommand implements Command<Response<RoleDto>> {

    private final String roleId;

    public GetRolCommand(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}


