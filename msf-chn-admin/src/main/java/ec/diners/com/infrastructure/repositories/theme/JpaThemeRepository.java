package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.infrastructure.modelsDb.theme.ThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaThemeRepository extends JpaRepository<ThemeModel, Long> {

    Optional<ThemeModel> findByName(String name);

}
