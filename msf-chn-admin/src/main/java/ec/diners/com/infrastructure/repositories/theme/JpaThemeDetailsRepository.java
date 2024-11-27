package ec.diners.com.infrastructure.repositories.theme;

import ec.diners.com.domain.entities.theme.ThemeDetailsView;
import ec.diners.com.infrastructure.modelsDb.theme.ThemeDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaThemeDetailsRepository extends JpaRepository<ThemeDetailModel,Long> {

    @Query("SELECT td.id AS id, td.uuid AS uuid, td.name AS name, td.value AS value from ThemeDetailModel td inner join td.theme t where t.id = :themeId and t.isActive = :isActive order by td.name asc")
    List<ThemeDetailsView> findThemeDetailByIdTheme(@Param("themeId") Long themeId, @Param("isActive") boolean isActive);
}
