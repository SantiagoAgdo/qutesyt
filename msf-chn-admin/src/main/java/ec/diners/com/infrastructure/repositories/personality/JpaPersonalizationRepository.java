package ec.diners.com.infrastructure.repositories.personality;

import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JpaPersonalizationRepository extends CrudRepository<PersonalizationModel, Long> {


    Optional<PersonalizationModel> findByDinersId(String dinersId);

    @Modifying
    @Query(value = "TRUNCATE TABLE personalization_home", nativeQuery = true)
    void truncateTable();

    Optional<List<PersonalizationModel>> findByDinersIdIn(List<String> dinersIds);


    Page<PersonalizationModel> findAll(Pageable pageable);

}
