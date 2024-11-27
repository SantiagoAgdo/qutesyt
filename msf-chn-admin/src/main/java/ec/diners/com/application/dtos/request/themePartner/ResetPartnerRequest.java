package ec.diners.com.application.dtos.request.themePartner;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPartnerRequest {
    @NotBlank(message = "dinersId es identificationNumber")
    private String identificationNumber;
}
