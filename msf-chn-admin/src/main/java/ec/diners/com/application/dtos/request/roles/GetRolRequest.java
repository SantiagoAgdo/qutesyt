package ec.diners.com.application.dtos.request.roles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRolRequest {

    @NotBlank(message = "roleId field can't be empty.")
    private String roleId;
}

