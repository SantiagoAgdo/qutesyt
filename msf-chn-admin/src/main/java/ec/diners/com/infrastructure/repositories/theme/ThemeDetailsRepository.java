package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.ThemeDetails;
import ec.diners.com.domain.entities.theme.ThemeDetailsView;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeDetailsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ThemeDetailsRepository implements IThemeDetailsRepository {

    private final JpaThemeDetailsRepository jpaThemeDetailsRepository;
    private final ThemeDetailsMapper mapper;

    public ThemeDetailsRepository(JpaThemeDetailsRepository jpaThemeDetailsRepository, ThemeDetailsMapper mapper) {
        this.jpaThemeDetailsRepository = jpaThemeDetailsRepository;
        this.mapper = mapper;
    }

    @Override
    public ThemeDetails findById(Long id) {
        return this.jpaThemeDetailsRepository.findById(id).map(this.mapper::toThemeDetails).orElse(null);
    }

    @Override
    public List<ThemeDetailsView> getThemeDetailsView(Long themeId) {
        return jpaThemeDetailsRepository.findThemeDetailByIdTheme(themeId, Boolean.TRUE);
    }

    @Override
    public Long save(ThemeDetails themeDetails) {
        return jpaThemeDetailsRepository.save(mapper.toThemeDetailsModel(themeDetails)).getId();
    }

    @Override
    public void update(ThemeDetails themeDetails) {
        jpaThemeDetailsRepository.save(mapper.toThemeDetailsModel(themeDetails));
    }
}
