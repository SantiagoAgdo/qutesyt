package ec.diners.com.application.dtos.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    @NotBlank(message = "Name field can't be empty.")
    private String name;

    private String lastName;
    private String identificationNumber;
    private String identificationType;
    private String telephoneNumber;
    private String photo;
    private String email;
    private String pwd;
    private String token;
    private boolean enabled;
}

