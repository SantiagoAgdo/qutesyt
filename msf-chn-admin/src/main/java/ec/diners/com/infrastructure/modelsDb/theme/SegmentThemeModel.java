package ec.diners.com.infrastructure.modelsDb.theme;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "segment_theme")
@Getter
@Setter
public class SegmentThemeModel extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "segment_id")
    private Long segmentId;

    @Column(name = "theme_segment_id")
    private Long themeSegmentId;

    @Column(name = "theme_campaign_id")
    private Long themeCampaignId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "segment_id", insertable = false, updatable = false)
    private SegmentModel segment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_segment_id", insertable = false, updatable = false)
    private ThemeModel themeSegment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_campaign_id", insertable = false, updatable = false)
    private ThemeModel themeCampaign;

    @PreUpdate
    public void prePersist(){
        this.updatedAt = new Date(System.currentTimeMillis());
    }

}
