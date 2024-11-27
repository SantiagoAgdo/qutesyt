package ec.diners.com.application.commands.roles.delete;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public class DeleteRolCommand implements Command<Response<String>> {

    private final String roleId;

    public DeleteRolCommand(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}
