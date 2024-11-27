package ec.diners.com.application.dtos.request.themeProduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductThemeRequest {

    @JsonIgnore
    private Long productThemeId;

    @NotNull(message = "campo themeId es requerido.")
    private Long themeId;

    @NotNull(message = "campo user es requerido.")
    private String user;

}
