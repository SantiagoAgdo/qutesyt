package ec.diners.com.application.dtos.request.themeProduct;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductThemeOrderRequest {
    @NotNull(message = "campo idProduct es requerido.")
    private List<Long> idProductList;
}
