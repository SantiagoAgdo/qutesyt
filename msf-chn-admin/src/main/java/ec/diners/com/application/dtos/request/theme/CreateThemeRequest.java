package ec.diners.com.application.dtos.request.theme;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateThemeRequest {

    @NotBlank(message = "campo nombre es requerido.")
    private String name;

    private String description;

    private String userCreate;

    @NotBlank(message = "campo detalle de tema es requerido.")
    private List<CreateDetailThemeRequest> detailTheme;
}
