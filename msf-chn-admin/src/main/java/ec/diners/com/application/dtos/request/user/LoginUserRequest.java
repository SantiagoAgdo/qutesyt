package ec.diners.com.application.dtos.request.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    private String email;
    private String pwd;
}
