package ec.diners.com.domain.interfaces.repositories.theme;

import ec.diners.com.domain.entities.theme.ThemeDetails;
import ec.diners.com.domain.entities.theme.ThemeDetailsView;

import java.util.List;

public interface IThemeDetailsRepository {
    ThemeDetails findById(Long id);
    List<ThemeDetailsView> getThemeDetailsView(Long themeId);
    Long save(ThemeDetails themeDetails);
    void update(ThemeDetails themeDetails);
}
