package ec.diners.com.application.dtos.request.themeSegment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SegmentThemeRequest {

    @NotNull(message = "campo segmentId es requerido.")
    private Long segmentId;

    @NotNull(message = "campo themeSegmentId es requerido.")
    private Long themeSegmentId;

    private Long themeCampaignId;

    @NotNull(message = "campo user es requerido.")
    private String user;

    @NotNull(message = "campo state es requerido.")
    private Integer state;

}
