package ec.diners.com.application.dtos.request.themePartner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePartnerThemeRequest {

    @NotBlank(message = "dinersId es identificationNumber")
    private String identificationNumber;
    @NotNull(message = "themeId es requerido")
    private Long themeId;

}
