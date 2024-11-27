package ec.diners.com.application.commands.themeSegment.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.themeSegment.UpdateSegmentThemeRequest;
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
public class UpdateSegmentThemeCommandHandler implements Command.Handler<UpdateSegmentThemeCommand, Response<Boolean>> {

    private final ISegmentThemeRepository segmentThemeRepository;
    private final IThemeRepository themeRepository;
    private final ISegmentRepository segmentRepository;
    private final IPersonalityChangeRepository personalityChangeRepository;

    public UpdateSegmentThemeCommandHandler(ISegmentThemeRepository segmentThemeRepository, IThemeRepository themeRepository, ISegmentRepository segmentRepository, IPersonalityChangeRepository personalityChangeRepository) {
        this.segmentThemeRepository = segmentThemeRepository;
        this.themeRepository = themeRepository;
        this.segmentRepository = segmentRepository;
        this.personalityChangeRepository = personalityChangeRepository;
    }

    @Override
    @Transactional
    public Response<Boolean> handle(UpdateSegmentThemeCommand updateSegmentThemeCommand) {
        log.info("*** Init updateSegmentThemeCommand **");
        var processResponse = new ProcessResponse<Boolean>();
        PersonalizationChangeModel personalizationChangeModel = null;
        if(CollectionUtils.isNotEmpty(updateSegmentThemeCommand.getLstSegmentTheme())) {
            log.info("*** Process Size... {}**", updateSegmentThemeCommand.getLstSegmentTheme().size());
            List<Long> lstSegmentIds= new ArrayList<>();
            updateSegmentThemeCommand.getLstSegmentTheme().stream().forEach(segmentTheme -> {
                if(segmentTheme.getState().equals(Constants.STATE_ACTIVE_CHANGE)){
                    lstSegmentIds.add(segmentTheme.getSegmentId());
                }
                this.updateSegmentTheme(segmentTheme);
            });
            if(CollectionUtils.isNotEmpty(lstSegmentIds)){
                //save personality_changes
                personalizationChangeModel = new PersonalizationChangeModel();
                personalizationChangeModel.setState(TaskPersonalityStatusEnum.PENDING.getValue());
                personalizationChangeModel.setName(TaskPersonalityNameEnum.UPDATE_SEGMENT_THEME.getValue());
                personalizationChangeModel.setCode(String.join(",", lstSegmentIds.stream().map(Object::toString).toArray(String[]::new)));
                personalityChangeRepository.save(personalizationChangeModel);
            }
        }
        log.info("*** End updateSegmentThemeCommand **");
        return processResponse.success(Boolean.TRUE);
    }

    private void updateSegmentTheme(UpdateSegmentThemeRequest updateSegmentThemeCommand){
        Segment segment = null;
        SegmentTheme config = null;

        segment = this.segmentRepository.findById(Long.valueOf(updateSegmentThemeCommand.getSegmentId()));

        if (Objects.isNull(segment))
            throw new NotFoundException("El Segmento con id: " + updateSegmentThemeCommand.getSegmentId() + " no existe");

        config = this.segmentThemeRepository.findById(updateSegmentThemeCommand.getId());

        if (Objects.isNull(config))
            throw new NotFoundException("La configuracion de tema con id: " + updateSegmentThemeCommand.getId() + " no existe");

        if (Objects.nonNull(updateSegmentThemeCommand.getThemeSegmentId())) {
            if (updateSegmentThemeCommand.getThemeSegmentId() != 0) {
                validateTheme(updateSegmentThemeCommand.getThemeSegmentId());
            }
        }

        if (Objects.nonNull(updateSegmentThemeCommand.getThemeCampaignId())) {
            if (updateSegmentThemeCommand.getThemeCampaignId() != 0) {
                validateTheme(updateSegmentThemeCommand.getThemeCampaignId());
            }
        }

        SegmentTheme objectToSave = new SegmentTheme();
        objectToSave.setId(config.getId());
        objectToSave.setSegmentId(config.getSegmentId());
        if (Objects.nonNull(updateSegmentThemeCommand.getThemeCampaignId())) {
            if (updateSegmentThemeCommand.getThemeCampaignId() != 0) {
                objectToSave.setThemeCampaignId(updateSegmentThemeCommand.getThemeCampaignId());
            } else {
                objectToSave.setThemeCampaignId(null);
            }
        }

        if (Objects.nonNull(updateSegmentThemeCommand.getThemeSegmentId())) {
            if (updateSegmentThemeCommand.getThemeSegmentId() != 0) {
                objectToSave.setThemeSegmentId(updateSegmentThemeCommand.getThemeSegmentId());
            } else {
                objectToSave.setThemeSegmentId(null);
            }
        }
        objectToSave.setCreatedAt(config.getCreatedAt());
        objectToSave.setCreatorUserId(config.getCreatorUserId());
        objectToSave.setIsActive(Boolean.TRUE);
        objectToSave.setUpdaterUserId(updateSegmentThemeCommand.getUser());

        segmentThemeRepository.save(objectToSave);
    }

    private void validateTheme(Long themeId) {
        Theme theme = this.themeRepository.findById(themeId);
        if (Objects.isNull(theme)) throw new NotFoundException("El Tema con id: " + themeId + " no existe");
    }

}
