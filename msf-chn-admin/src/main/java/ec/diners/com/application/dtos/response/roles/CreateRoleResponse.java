package ec.diners.com.application.dtos.response.roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoleResponse {

    private String roleId;
    public CreateRoleResponse(String roleId){
        this.roleId = roleId;
    }

}
