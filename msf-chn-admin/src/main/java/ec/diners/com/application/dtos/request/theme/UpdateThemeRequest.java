package ec.diners.com.application.dtos.request.theme;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateThemeRequest {
    @NotNull(message = "campo id es requerido.")
    private Long id;
    @NotBlank(message = "campo nombre es requerido.")
    private String name;
    private String description;
    private String updateUser;
    @NotBlank(message = "campo detalle de tema es requerido.")
    private List<UpdateThemeDetailRequest> detailTheme;
}
