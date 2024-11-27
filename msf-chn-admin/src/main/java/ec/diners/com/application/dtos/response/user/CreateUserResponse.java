package ec.diners.com.application.dtos.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponse {

    private String userId;
    public CreateUserResponse(String roleId){
        this.userId = userId;
    }

}
