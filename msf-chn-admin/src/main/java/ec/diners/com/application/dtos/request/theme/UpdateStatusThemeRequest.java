package ec.diners.com.application.dtos.request.theme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatusThemeRequest {
    @NotNull(message = "campo id es requerido.")
    private Long id;
    private String updateUser;
    @NotBlank(message = "campo isActive es requerido.")
    private Boolean isActive;
}
