package ec.diners.com.application.dtos.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecoverPasswordRequest {

    @NotBlank(message = "email field can't be empty.")
    private String email;
}

