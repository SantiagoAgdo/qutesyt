package ec.diners.com.application.dtos.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEnabledRequest {
    @NotBlank(message = "Email cannot be empty.")
    private String email;

    private boolean enabled;
}
