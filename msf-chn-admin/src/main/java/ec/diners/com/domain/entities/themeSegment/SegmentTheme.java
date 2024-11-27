package ec.diners.com.domain.entities.themeSegment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SegmentTheme {

    private Long id;

    private Long segmentId;

    private Long themeSegmentId;

    private Long themeCampaignId;

    private Date createdAt;

    private String creatorUserId;

    private Boolean isActive;

    private String updaterUserId;

}
