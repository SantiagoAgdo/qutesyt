package ec.diners.com.infrastructure.repositories.partnerIntegration;

import ec.diners.com.application.dtos.response.partnerIntegration.PartnerIntegrationDto;
import ec.diners.com.application.dtos.response.themePartner.PartnerThemeView;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.infrastructure.modelsDb.partnerIntegration.PartnerIntegrationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PartnerIntegrationRepository implements IPartnerIntegrationRepository {

    private final JpaPartnerIntegrationrRepository jpaPartnerIntegrationrRepository;
    private final PartnerIntegrationMapper partnerIntegrationMapper;

    @Override
    public void deleteAll() {
        jpaPartnerIntegrationrRepository.truncateTable();
    }

    @Override
    public void saveAll(List<PartnerIntegrationModel> lstPartnerIntegrationModel) {
        jpaPartnerIntegrationrRepository.saveAll(lstPartnerIntegrationModel);
    }

    @Override
    public Page<PartnerIntegrationModel> findPartnerPage(Pageable pageable) {
        return jpaPartnerIntegrationrRepository.findByIsRegistered(pageable, Boolean.TRUE);
    }

    @Override
    public PartnerIntegrationModel findByDinersId(String dinersId) {
        var obj = jpaPartnerIntegrationrRepository.findByDinersId(dinersId);
        return obj.orElse(null);

    }

    @Override
    public Long findThemeId(Long themeId) {
        return jpaPartnerIntegrationrRepository.findThemeId(themeId);
    }

    @Override
    public void update(PartnerIntegrationModel partner) {
        //Save changes
        jpaPartnerIntegrationrRepository.save(partner);
    }

    @Override
    public List<PartnerThemeView> listPartner() {
        return jpaPartnerIntegrationrRepository.listPartner(Boolean.TRUE);
    }

    @Override
    public List<PartnerIntegrationDto> findByDinersIds(List<String> ids) {
        return jpaPartnerIntegrationrRepository.findByDinersIdIn(ids).map(this.partnerIntegrationMapper::toEntities).orElse(null);
    }

    @Override
    public List<String> findAllBySegmendId(Integer codeSegment) {
        return jpaPartnerIntegrationrRepository.findAllByCodeSegment(codeSegment, Boolean.TRUE);
    }

    @Override
    public List<String> findAllByProducts(String codeProduct) {
        return jpaPartnerIntegrationrRepository.findByCodeProducts(codeProduct, Boolean.TRUE);
    }

    @Override
    public List<String> findAllBySegmendIds(List<Integer> codeSegments) {
        return jpaPartnerIntegrationrRepository.findAllByInCodeSegments(codeSegments, Boolean.TRUE);
    }

    @Override
    public String sincronizarPartnetFromUser(String dinersId,String name,String cardProducts,Integer codeSegment){
        return jpaPartnerIntegrationrRepository.sincronizarPartnetFromUser(dinersId,name,cardProducts,codeSegment);
    }

}
