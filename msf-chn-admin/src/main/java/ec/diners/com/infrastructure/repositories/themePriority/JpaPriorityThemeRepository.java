package ec.diners.com.infrastructure.repositories.themePriority;

import ec.diners.com.infrastructure.modelsDb.theme.PriorityThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPriorityThemeRepository extends JpaRepository<PriorityThemeModel,Long> {
    List<PriorityThemeModel> findAllByOrderByPriorityAsc();
}
