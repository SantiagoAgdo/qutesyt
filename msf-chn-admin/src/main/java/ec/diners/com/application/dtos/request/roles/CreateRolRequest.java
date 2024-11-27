package ec.diners.com.application.dtos.request.roles;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRolRequest {

    @NotBlank(message = "Name field can't be empty.")
    private String name;
    private String description;
}

