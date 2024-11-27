package ec.diners.com.application.commands.themeSegment.register;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.themeSegment.SegmentThemeRequest;
import ec.diners.com.domain.interfaces.repositories.personalityChange.IPersonalityChangeRepository;
import ec.diners.com.domain.entities.segment.Segment;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.domain.exceptions.NotFoundException;
import ec.diners.com.domain.interfaces.repositories.segment.ISegmentRepository;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.domain.interfaces.repositories.themeSegment.ISegmentThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.utils.Constants;
import ec.diners.com.domain.utils.UtilDates;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationChangeModel;
import ec.diners.com.infrastructure.utils.TaskPersonalityNameEnum;
import ec.diners.com.infrastructure.utils.TaskPersonalityStatusEnum;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class CreateSegmentThemeCommandHandler implements Command.Handler<CreateSegmentThemeCommand, Response<Boolean>> {

    private final ISegmentRepository segmentRepository;
    private final ISegmentThemeRepository segmentThemeRepository;
    private final IThemeRepository themeRepository;
    private final IPersonalityChangeRepository personalityChangeRepository;

    public CreateSegmentThemeCommandHandler(ISegmentRepository segmentRepository, ISegmentThemeRepository segmentThemeRepository, IThemeRepository themeRepository, IPersonalityChangeRepository personalityChangeRepository) {
        this.segmentRepository = segmentRepository;
        this.segmentThemeRepository = segmentThemeRepository;
        this.themeRepository = themeRepository;
        this.personalityChangeRepository = personalityChangeRepository;
    }

    @Override
    @Transactional
    public Response<Boolean> handle(CreateSegmentThemeCommand createSegmentThemeCommand) {
        log.info("*** Init createSegmentThemeCommand **");
        var processResponse = new ProcessResponse<Boolean>();
        PersonalizationChangeModel personalizationChangeModel = null;
        if(CollectionUtils.isNotEmpty(createSegmentThemeCommand.getLstSegmentTheme())){
            log.info("*** Process Size... {}**", createSegmentThemeCommand.getLstSegmentTheme().size());
            List<Long> lstSegmentIds= new ArrayList<>();
            createSegmentThemeCommand.getLstSegmentTheme().stream().forEach(segmentTheme -> {
                if(segmentTheme.getState().equals(Constants.STATE_ACTIVE_CHANGE)){
                    lstSegmentIds.add(segmentTheme.getSegmentId());
                }
                this.createSegmentTheme(segmentTheme);
            });
            if(CollectionUtils.isNotEmpty(lstSegmentIds)){
                //save personality_changes
                personalizationChangeModel = new PersonalizationChangeModel();
                personalizationChangeModel.setState(TaskPersonalityStatusEnum.PENDING.getValue());
                personalizationChangeModel.setName(TaskPersonalityNameEnum.CREATE_SEGMENT_THEME.getValue());
                personalizationChangeModel.setCode(String.join(",", lstSegmentIds.stream().map(Object::toString).toArray(String[]::new)));
                personalityChangeRepository.save(personalizationChangeModel);
            }
        }
        log.info("*** End createSegmentThemeCommand **");
        return processResponse.success(Boolean.TRUE);
    }

    private void createSegmentTheme(SegmentThemeRequest createSegmentThemeCommand){
        Segment segment = null;
        SegmentTheme segmentTheme = null;

        segment = this.segmentRepository.findById(createSegmentThemeCommand.getSegmentId());

        if (Objects.isNull(segment))
            throw new NotFoundException("El Segmento con id: " + createSegmentThemeCommand.getSegmentId() + " no existe");

        segmentTheme = this.segmentThemeRepository.findBySegmentIdAndIsActiveTrue(segment.getId());

        if (Objects.nonNull(segmentTheme))
            throw new NotFoundException("El Segmento con id: " + createSegmentThemeCommand.getSegmentId() + " ya existe, no puede volver a registrarse");

        validateTheme(createSegmentThemeCommand.getThemeSegmentId());

        if (Objects.nonNull(createSegmentThemeCommand.getThemeCampaignId())) {
            if (createSegmentThemeCommand.getThemeCampaignId() != 0) {
                validateTheme(createSegmentThemeCommand.getThemeCampaignId());
            }
        }

        SegmentTheme objectToSave = new SegmentTheme();
        objectToSave.setSegmentId(createSegmentThemeCommand.getSegmentId());
        if (Objects.nonNull(createSegmentThemeCommand.getThemeCampaignId())) {
            if (createSegmentThemeCommand.getThemeCampaignId() != 0) {
                objectToSave.setThemeCampaignId(createSegmentThemeCommand.getThemeCampaignId());
            } else {
                objectToSave.setThemeCampaignId(null);
            }
        }
        objectToSave.setThemeSegmentId(createSegmentThemeCommand.getThemeSegmentId());
        objectToSave.setCreatedAt(UtilDates.getNowTodayDate());
        objectToSave.setCreatorUserId(createSegmentThemeCommand.getUser());
        objectToSave.setIsActive(Boolean.TRUE);

        segmentThemeRepository.save(objectToSave);
    }

    private void validateTheme(Long themeId) {
        Theme theme = this.themeRepository.findById(themeId);
        if (Objects.isNull(theme)) throw new NotFoundException("El Tema con id: " + themeId + " no existe");
    }

}
