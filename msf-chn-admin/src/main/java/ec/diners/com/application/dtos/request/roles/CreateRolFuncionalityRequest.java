package ec.diners.com.application.dtos.request.roles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRolFuncionalityRequest {

    @NotBlank(message = "Name field can't be empty.")
    private Long roleId;
    private Long funcionalityId;
}

