package ec.diners.com.domain.entities.personality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalizationDto {

    private Long id;
    private String dinersId;
    private String themeId;
    private String bannerIds;
    private String moduleYourExperiencesIds;
    private String moduleConfig;
    private String recommendedByRestaurant;
    private String recommendedByPromotions;
    private String recommendedByFashions;
    private Boolean isModeChallenger;
    private String nameIcon;
    private LocalDateTime registrationDate;

}
