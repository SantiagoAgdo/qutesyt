package ec.diners.com.application.dtos.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {

    @NotBlank(message = "Email cannot be empty.")
    private String email;

    @NotBlank(message = "Old password cannot be empty.")
    private String oldPassword;

    @NotBlank(message = "New password cannot be empty.")
    private String newPassword;
}
