package ec.diners.com.infrastructure.modelsDb.personality;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "personalization")
@Getter
@Setter
public class PersonalizationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diners_id")
    private String dinersId;

    @Column(name = "theme_id")
    private String themeId;

    @Column(name = "banner_ids")
    private String bannerIds;

    @Column(name = "module_your_experiences_ids")
    private String moduleYourExperiencesIds;

    @Column(name = "module_config")
    private String moduleConfig;

    @Column(name = "recommended_by_restaurant")
    private String recommendedByRestaurant;

    @Column(name = "recommended_by_promotions")
    private String recommendedByPromotions;

    @Column(name = "recommended_by_fashions")
    private String recommendedByFashions;

    @Column(name = "is_mode_challenger")
    private Boolean isModeChallenger;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Override
    public String toString() {
        return "PersonalizationHomeModel{" +
                "id=" + id +
                ", dinersId='" + dinersId + '\'' +
                ", themeId='" + themeId + '\'' +
                ", bannerIds='" + bannerIds + '\'' +
                ", moduleYourExperiencesIds='" + moduleYourExperiencesIds + '\'' +
                ", moduleConfig='" + moduleConfig + '\'' +
                ", recommendedByRestaurant='" + recommendedByRestaurant + '\'' +
                ", recommendedByPromotions='" + recommendedByPromotions + '\'' +
                ", recommendedByFashions='" + recommendedByFashions + '\'' +
                ", isModeChallenger=" + isModeChallenger +
                ", registrationDate=" + registrationDate +
                '}';
    }
}