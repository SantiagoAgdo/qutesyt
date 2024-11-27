package ec.diners.com.domain.interfaces.repositories.themePriority;

import ec.diners.com.domain.entities.themePriority.PriorityTheme;

import java.util.List;

public interface IPriorityThemeRepository {
    List<PriorityTheme> getPriorityThemes();
}
