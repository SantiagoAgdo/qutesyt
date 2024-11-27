package ec.diners.com.application.dtos.request.themeProduct;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductThemeRequest {

    @NotNull(message = "campo codeProduct es requerido.")
    private List<String> codeProductList;

    @NotNull(message = "campo themeId es requerido.")
    private Long themeId;

    @NotNull(message = "campo user es requerido.")
    private String user;

}
