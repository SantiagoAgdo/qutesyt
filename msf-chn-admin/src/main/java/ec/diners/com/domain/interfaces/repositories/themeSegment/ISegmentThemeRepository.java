package ec.diners.com.domain.interfaces.repositories.themeSegment;

import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.domain.entities.themeSegment.SegmentThemeListAllView;

import java.util.List;

public interface ISegmentThemeRepository {

    SegmentTheme findBySegmentIdAndIsActiveTrue(Long segmentId);
    List<SegmentTheme> getAllRecordsActives();
    List<SegmentThemeListAllView> findAllRecordsActives();
    SegmentTheme findById(Long id);
    Long save(SegmentTheme segmentTheme);
    Long update(SegmentTheme segmentTheme);
    Boolean countSegmentByThemeId(Integer themeId);
    Boolean countCampaignByThemeId(Integer themeId);

}
