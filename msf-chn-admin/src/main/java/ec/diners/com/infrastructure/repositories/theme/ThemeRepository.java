package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.infrastructure.modelsDb.theme.ThemeModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class ThemeRepository implements IThemeRepository {

    private final JpaThemeRepository jpaThemeRepository;

    private final ThemeMapper mapper;

    public ThemeRepository(JpaThemeRepository jpaThemeRepository, ThemeMapper mapper) {
        this.jpaThemeRepository = jpaThemeRepository;
        this.mapper = mapper;
    }

    @Override
    public Theme findById(Long id) {
        return this.jpaThemeRepository.findById(id).map(this.mapper::toTheme).orElse(null);
    }

    @Override
    public Theme findByName(String name) {
        return this.jpaThemeRepository.findByName(name).map(this.mapper::toTheme).orElse(null);
    }

    @Override
    public List<Theme> listTheme() {
        Iterable<ThemeModel> iterable = jpaThemeRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toTheme)
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Theme theme) {
        return this.jpaThemeRepository.save(mapper.toThemeModel(theme)).getId();
    }

    @Override
    public void update(Theme theme) {
        this.jpaThemeRepository.save(mapper.toThemeModel(theme));
    }
}
