package ec.diners.com.application.dtos.response.roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRolFuncionalityResponse {

    private String roleId;
    public CreateRolFuncionalityResponse(String roleId){
        this.roleId = roleId;
    }

}
