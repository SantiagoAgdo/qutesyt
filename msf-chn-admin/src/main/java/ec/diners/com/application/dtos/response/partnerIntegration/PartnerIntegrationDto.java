package ec.diners.com.application.dtos.response.partnerIntegration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerIntegrationDto {

    private Long id;
    private String dinersId;
    private String name;
    private Long themeId;
    private String cardProducts;
    private Integer codeSegment;
    private Boolean isRegistered;

    private Double latitude;
    private Double longitude;

}
