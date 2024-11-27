package ec.diners.com.domain.interfaces.repositories.partnerIntegration;

import ec.diners.com.application.dtos.response.partnerIntegration.PartnerIntegrationDto;
import ec.diners.com.application.dtos.response.themePartner.PartnerThemeView;
import ec.diners.com.infrastructure.modelsDb.partnerIntegration.PartnerIntegrationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPartnerIntegrationRepository {

    void deleteAll();
    void saveAll(List<PartnerIntegrationModel> lstPartnerIntegrationModel);
    Page<PartnerIntegrationModel> findPartnerPage(Pageable pageable);
    PartnerIntegrationModel findByDinersId(String dinersId);
    Long findThemeId(Long themeId);
    void update(PartnerIntegrationModel partner);
    List<PartnerThemeView> listPartner();
    List<PartnerIntegrationDto> findByDinersIds(List<String> ids);
    List<String> findAllBySegmendId(Integer codeSegment);
    List<String> findAllByProducts(String codeProduct);
    List<String> findAllBySegmendIds(List<Integer> codeSegments);
    String sincronizarPartnetFromUser(String dinersId,String name,String cardProducts,Integer codeSegment);

}
