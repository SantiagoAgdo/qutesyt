package ec.diners.com.infrastructure.repositories.personalityChange;

import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationChangeModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for JpaRepository
 * @author opinno
 * @version 1.0
 */
public interface JpaPersonalityChangeRepository extends JpaRepository<PersonalizationChangeModel, Long> {

    List<PersonalizationChangeModel> findByName(String name);

    List<PersonalizationChangeModel> findByStateIn(List<String> states);

    List<PersonalizationChangeModel> findByNameInAndState(List<String> names, String state);

    @Query("SELECT p FROM PersonalizationChangeModel p WHERE p.name IN :names AND p.state = :state")
    List<PersonalizationChangeModel> findIdsByNameInAndState(@Param("names") List<String> names, @Param("state") String state);

    @Transactional
    void deleteByName(String name);

    @Transactional
    void deleteById(Long id);

    Boolean existsByState(String state);

}
