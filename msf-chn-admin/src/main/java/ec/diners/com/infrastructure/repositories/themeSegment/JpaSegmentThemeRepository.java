package ec.diners.com.infrastructure.repositories.themeSegment;

import ec.diners.com.domain.entities.themeSegment.SegmentThemeListAllView;
import ec.diners.com.infrastructure.modelsDb.theme.SegmentThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaSegmentThemeRepository extends JpaRepository<SegmentThemeModel, Long> {
    Optional<SegmentThemeModel> findBySegmentIdAndIsActiveTrue(Long segmentId);

    List<SegmentThemeModel> findAllByIsActiveTrue();

    @Query("select st.id as id, st.segmentId as segmentId, s.name as segmentName, " +
            "COALESCE((st.themeSegmentId),0) as themeSegmentId, \n" +
            "COALESCE((select t.name from ThemeModel t where t.id = st.themeSegmentId),'') as themeSegmentName, \n" +
            "COALESCE(st.themeCampaignId,0) as themeCampaignId, \n" +
            "COALESCE ((select t.name from ThemeModel t where t.id = st.themeCampaignId),'') as themeCampaignName\n" +
            "from SegmentThemeModel st \n" +
            "join SegmentModel s on s.id = st.segmentId where st.isActive = true")
    List<SegmentThemeListAllView> findAllRecordsActives();

    Optional<SegmentThemeModel> findByIdAndIsActiveTrue(Long id);
    @Query("select count(st.themeSegmentId) from SegmentThemeModel st where st.themeSegmentId=:themeId")
    Long countSegmentByThemeId(Integer themeId);

    @Query("select count(st.themeCampaignId) from SegmentThemeModel st where st.themeCampaignId=:themeId")
    Long countCampaignByThemeId(Integer themeId);
}
