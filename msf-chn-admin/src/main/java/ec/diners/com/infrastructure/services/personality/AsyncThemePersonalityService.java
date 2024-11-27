package ec.diners.com.infrastructure.services.personality;

import ec.diners.com.application.dtos.response.partnerIntegration.PartnerIntegrationDto;
import ec.diners.com.domain.entities.personality.PersonalizationDto;
import ec.diners.com.domain.entities.personality.PersonalizationValidationDto;
import ec.diners.com.domain.entities.themePriority.PriorityTheme;
import ec.diners.com.domain.exceptions.NotFoundException;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.domain.interfaces.repositories.personality.IPersonalizationRepository;
import ec.diners.com.domain.interfaces.repositories.personalityChange.IPersonalityChangeRepository;
import ec.diners.com.domain.interfaces.repositories.themePriority.IPriorityThemeRepository;
import ec.diners.com.domain.interfaces.services.personality.IAsyncThemePersonalityService;
import ec.diners.com.domain.utils.Constants;
import ec.diners.com.domain.utils.UtilDates;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationChangeModel;
import ec.diners.com.infrastructure.utils.TaskPersonalityStatusEnum;
import ec.diners.com.infrastructure.utils.ThemesPriority.PartnerThemeUtil;
import ec.diners.com.infrastructure.utils.ThemesPriority.ProductThemeUtil;
import ec.diners.com.infrastructure.utils.ThemesPriority.SegmentThemeUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncThemePersonalityService implements IAsyncThemePersonalityService {

    private final IPersonalityChangeRepository personalityChangeRepository;
    private final IPersonalizationRepository personalizationRepository;
    private final IPartnerIntegrationRepository partnerIntegrationRepository;
    private final IPriorityThemeRepository priorityThemeRepository;

    private final SegmentThemeUtil segmentThemeUtil;
    private final ProductThemeUtil productThemeUtil;

    public AsyncThemePersonalityService(IPersonalityChangeRepository personalityChangeRepository, IPersonalizationRepository personalizationRepository, IPartnerIntegrationRepository partnerIntegrationRepository, IPriorityThemeRepository priorityThemeRepository, SegmentThemeUtil segmentThemeUtil, ProductThemeUtil productThemeUtil) {
        this.personalityChangeRepository = personalityChangeRepository;
        this.personalizationRepository = personalizationRepository;
        this.partnerIntegrationRepository = partnerIntegrationRepository;
        this.priorityThemeRepository = priorityThemeRepository;
        this.segmentThemeUtil = segmentThemeUtil;
        this.productThemeUtil = productThemeUtil;
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Boolean> validationThemesByPriority(PersonalizationValidationDto personalizationValidationDto) {
        List<PersonalizationChangeModel> lstChangeThemes = null;
        String themeFindId = null;
        List<PersonalizationDto> lstPersonality = null;
        List<PartnerIntegrationDto> lstPartners = null;
        PartnerIntegrationDto partnerIntegrationDto = null;

        if(CollectionUtils.isEmpty(personalizationValidationDto.getLstPartners())){
            throw new NotFoundException("Error.: lista de dinersId esta vacio.");
        }

        //Get all records by dinersId
        lstPersonality = personalizationRepository.findAllByDinersId(personalizationValidationDto.getLstPartners());
        lstPartners = partnerIntegrationRepository.findByDinersIds(personalizationValidationDto.getLstPartners());

        if(CollectionUtils.isNotEmpty(lstPersonality) && CollectionUtils.isNotEmpty(lstPartners)) {
            int cont = 0;
            for(PersonalizationDto personalizationDto : lstPersonality) {
                cont = cont+1;
                log.info(" *** THEME CONTEO A PROCESAR DEL TOTAL*** {} {}", cont, lstPersonality.size());
                partnerIntegrationDto = this.getThemeIdByUser(personalizationDto.getDinersId(), lstPartners);
                if(partnerIntegrationDto != null){
                    themeFindId = this.getThemeIdByPriorityLevel(partnerIntegrationDto.getThemeId(), partnerIntegrationDto.getCardProducts(), partnerIntegrationDto.getCodeSegment());
                    if(StringUtils.isNotBlank(themeFindId)){
                        personalizationDto.setThemeId(themeFindId);
                        personalizationRepository.save(personalizationDto);
                    }
                }
            }

            //Save all status completed
            lstChangeThemes = personalityChangeRepository.findByNameAndState(personalizationValidationDto.getNameGroupOperation(), TaskPersonalityStatusEnum.IN_PROGRESS.getValue());
            if(CollectionUtils.isNotEmpty(lstChangeThemes)){
                lstChangeThemes.forEach(model ->{
                    model.setState(TaskPersonalityStatusEnum.COMPLETED.getValue());
                    model.setEndDate(UtilDates.getCurrentDate());
                    personalityChangeRepository.save(model);
                });
            }
        }
       return CompletableFuture.completedFuture(true);
    }

    private String getThemeIdByPriorityLevel(Long themeIdByUser, String mainProducts, Integer codeSegment) {
        // Obtener la lista de prioridades
        List<PriorityTheme> priorityThemes = priorityThemeRepository.getPriorityThemes();

        // Retornar el ID del tema basado en la prioridad
        Long themeId = CollectionUtils.emptyIfNull(priorityThemes)
                .stream()
                .map(priorityTheme -> getThemeId(priorityTheme.getName(), mainProducts, codeSegment, themeIdByUser))
                .filter(theme -> !Constants.PARAM_DEFAUL_THEME_APP.equals(theme)) // Filtrar temas válidos
                .findFirst()
                .orElse(Constants.PARAM_DEFAUL_THEME_APP); // Valor por defecto si no hay coincidencias

        return themeId.toString();
    }

    private Long getThemeId(String namePriority, String mainProducts, Integer codeSegment, Long themeIdByUser) {
        Long themeId = Constants.PARAM_DEFAUL_THEME_APP;
        switch (namePriority) {
            case Constants.PRIORITY_USER:
                themeId = PartnerThemeUtil.getThemeUser(themeIdByUser);
                break;
            case Constants.PRIORITY_PRODUCT:
                themeId = productThemeUtil.getProductThemeId(mainProducts);
                break;
            case Constants.PRIORITY_CAMPAING:
                themeId = segmentThemeUtil.getSegmentThemeOrCampaignTheme(codeSegment, Constants.CAMPAIGN);
                break;
            case Constants.PRIORITY_SEGMENT:
                themeId = segmentThemeUtil.getSegmentThemeOrCampaignTheme(codeSegment, Constants.SEGMENT);
                break;
        }
        return themeId;
    }

    private PartnerIntegrationDto getThemeIdByUser(String dinersId, List<PartnerIntegrationDto> lstPartners) {
        Optional<PartnerIntegrationDto> partnerIntegrationDto = lstPartners.stream()
                .filter(partner -> partner.getDinersId().equals(dinersId))
                .findFirst();
        return partnerIntegrationDto.orElse(null);
    }

}