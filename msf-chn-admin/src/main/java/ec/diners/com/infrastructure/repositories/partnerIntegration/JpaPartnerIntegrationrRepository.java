package ec.diners.com.infrastructure.repositories.partnerIntegration;

import ec.diners.com.application.dtos.response.themePartner.PartnerThemeView;
import ec.diners.com.infrastructure.modelsDb.partnerIntegration.PartnerIntegrationModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaPartnerIntegrationrRepository extends JpaRepository<PartnerIntegrationModel, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE partner_integration", nativeQuery = true)
    void truncateTable();

    Page<PartnerIntegrationModel> findByIsRegistered(Pageable pageable, Boolean isRegistered);

    Optional<PartnerIntegrationModel> findByDinersId(String dinersId);

    @Query("SELECT count(p.themeId) FROM PartnerIntegrationModel p WHERE p.themeId = :themeId")
    Long findThemeId(Long themeId);

    @Query("SELECT p.id as id,p.name as name,p.dinersId as identificationNumber, p.themeId as themeId" +
            " FROM PartnerIntegrationModel p WHERE  p.themeId IS NOT NULL")
    List<PartnerThemeView> listPartner(Boolean enabled);

    Optional<List<PartnerIntegrationModel>> findByDinersIdIn(List<String> dinersIds);

    @Query("SELECT p.dinersId FROM PartnerIntegrationModel p WHERE p.codeSegment = :codeSegments and p.isRegistered = :isRegistered")
    List<String> findAllByCodeSegment(Integer codeSegment, Boolean isRegistered);

    @Query("SELECT p.dinersId FROM PartnerIntegrationModel p WHERE p.codeSegment IN :codeSegments and p.isRegistered = :isRegistered")
    List<String> findAllByInCodeSegments(@Param("codeSegments") List<Integer> codeSegments, Boolean isRegistered);

    @Query(value = "SELECT p.diners_id FROM partner_integration p WHERE FIND_IN_SET(:code, REPLACE(p.card_products, '|', ',')) > 0 and p.is_registered = :isRegistered", nativeQuery = true)
    List<String> findByCodeProducts(@Param("code") String code, @Param("isRegistered") Boolean isRegistered);



    @Query(value = "CALL sp_sincronizarPartnetFromUser(:dinersId,:name,:cardProducts,:codeSegment);", nativeQuery = true)
    String sincronizarPartnetFromUser(@Param("dinersId") String dinersId,
                                            @Param("name") String name,
                                            @Param("cardProducts") String cardProducts,
                                            @Param("codeSegment") Integer codeSegment
    );


}
