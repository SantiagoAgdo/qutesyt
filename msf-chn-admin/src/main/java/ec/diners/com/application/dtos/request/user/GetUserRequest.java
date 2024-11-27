package ec.diners.com.application.dtos.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserRequest {

    @NotBlank(message = "id field can't be empty.")
    private Long id;

}

