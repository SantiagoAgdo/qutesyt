package ec.diners.com.application.dtos.request.themeSegment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSegmentThemeRequest {

    private Long id;

    private Long segmentId;

    private Long themeSegmentId;

    private Long themeCampaignId;

    @NotNull(message = "campo user es requerido.")
    private String user;

    private Integer state;

}
