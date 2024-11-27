package ec.diners.com.application.dtos.response.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecoverUserResponse {

    private String email;
    private String pwd;

    public RecoverUserResponse(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
