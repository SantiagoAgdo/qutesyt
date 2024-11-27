package ec.diners.com.application.commands.theme.updateStatus;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.theme.UpdateThemeResponse;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class UpdateStatusThemeCommandHandler implements Command.Handler<UpdateStatusThemeCommand, Response<UpdateThemeResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStatusThemeCommandHandler.class);
    private final IThemeRepository themeRepository;

    public UpdateStatusThemeCommandHandler(IThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Transactional
    @Override
    public Response<UpdateThemeResponse> handle(UpdateStatusThemeCommand updateThemeCommand) {
        var processResponse = new ProcessResponse<UpdateThemeResponse>();

        //buscamos el id del tema para actualizarlo
        var objTheme = themeRepository.findById(updateThemeCommand.getId());
        logger.info("VALOR DEL ID:{}", Objects.nonNull(objTheme) ? objTheme.getId() : null );

        if (Objects.nonNull(objTheme)) {
            objTheme.setId(objTheme.getId());
            objTheme.setName(objTheme.getName());
            objTheme.setDescription(objTheme.getDescription());
            objTheme.setUserUpdate(updateThemeCommand.getUpdateUser());
            objTheme.setUpdatedAt(new Date());
            objTheme.setIsActive(updateThemeCommand.getIsActive());
            themeRepository.update(objTheme);
        }else{
            return processResponse.error(new Response<>(new UpdateThemeResponse(-1L)));
        }
        return processResponse.success(new UpdateThemeResponse(objTheme.getId()));
    }
}
