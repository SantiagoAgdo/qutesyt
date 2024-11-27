package ec.diners.com.infrastructure.modelsDb.partnerIntegration;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "partner_integration")
@Getter
@Setter
public class PartnerIntegrationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dinersId;
    private String name;
    private Long themeId;
    private String cardProducts;
    private Integer codeSegment;
    private Boolean isRegistered;

    @Column(name = "last_latitude")
    private Double latitude;

    @Column(name = "last_longitude")
    private Double longitude;

}
