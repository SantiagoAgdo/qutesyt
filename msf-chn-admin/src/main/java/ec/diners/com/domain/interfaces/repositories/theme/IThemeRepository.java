package ec.diners.com.domain.interfaces.repositories.theme;

import ec.diners.com.domain.entities.theme.Theme;

import java.util.List;

public interface IThemeRepository {

    Theme findById(Long id);
    Theme findByName(String name);
    List<Theme> listTheme();
    Long save(Theme theme);
    void update(Theme theme);

}
