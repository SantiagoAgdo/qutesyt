package ec.diners.com.infrastructure.repositories.themePriority;

import ec.diners.com.domain.entities.themePriority.PriorityTheme;
import ec.diners.com.domain.interfaces.repositories.themePriority.IPriorityThemeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PriorityThemeRepository implements IPriorityThemeRepository {

    private final JpaPriorityThemeRepository jpaPriorityThemeRepository;

    private final PriorityThemeMapper mapper;


    public PriorityThemeRepository(JpaPriorityThemeRepository jpaPriorityThemeRepository, PriorityThemeMapper mapper) {
        this.jpaPriorityThemeRepository = jpaPriorityThemeRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PriorityTheme> getPriorityThemes() {
        var objPriorityTheme= jpaPriorityThemeRepository.findAllByOrderByPriorityAsc();
        return objPriorityTheme.stream().map(mapper::toPriorityTheme).collect(Collectors.toList());
    }
}
