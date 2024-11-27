package ec.diners.com.application.dtos.response.themeProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductThemeResponse {

    private String codeProduct;
    private String nameProduct;
    private String descriptionProduct;

}
