package ec.diners.com.infrastructure.repositories.themeSegment;

import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.domain.entities.themeSegment.SegmentThemeListAllView;
import ec.diners.com.domain.interfaces.repositories.themeSegment.ISegmentThemeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SegmentThemeRepository implements ISegmentThemeRepository {

    private final JpaSegmentThemeRepository jpaSegmentThemeRepository;

    private final SegmentThemeMapper mapper;

    public SegmentThemeRepository(JpaSegmentThemeRepository jpaSegmentThemeRepository, SegmentThemeMapper mapper) {
        this.jpaSegmentThemeRepository = jpaSegmentThemeRepository;
        this.mapper = mapper;
    }

    @Override
    public SegmentTheme findBySegmentIdAndIsActiveTrue(Long segmentId) {
        return this.jpaSegmentThemeRepository.findBySegmentIdAndIsActiveTrue(segmentId).map(this.mapper::toSegmentTheme).orElse(null);
    }

    @Override
    public List<SegmentTheme> getAllRecordsActives() {
        return this.jpaSegmentThemeRepository.findAllByIsActiveTrue().stream().map(this.mapper::toSegmentTheme).collect(Collectors.toList());
    }

    @Override
    public List<SegmentThemeListAllView> findAllRecordsActives() {
        return this.jpaSegmentThemeRepository.findAllRecordsActives();
    }

    @Override
    public SegmentTheme findById(Long id) {
        return this.jpaSegmentThemeRepository.findByIdAndIsActiveTrue(id).map(this.mapper::toSegmentTheme).orElse(null);
    }

    @Override
    public Long save(SegmentTheme segmentTheme) {
        return this.jpaSegmentThemeRepository.save(this.mapper.toSegmentThemeModel(segmentTheme)).getId();
    }

    @Override
    public Long update(SegmentTheme segmentTheme) {
        return this.jpaSegmentThemeRepository.save(this.mapper.toSegmentThemeModel(segmentTheme)).getId();
    }

    @Override
    public Boolean countSegmentByThemeId(Integer themeId) {
        return this.jpaSegmentThemeRepository.countSegmentByThemeId(themeId)>0;
    }

    @Override
    public Boolean countCampaignByThemeId(Integer themeId) {
        return this.jpaSegmentThemeRepository.countCampaignByThemeId(themeId)>0;
    }
}
