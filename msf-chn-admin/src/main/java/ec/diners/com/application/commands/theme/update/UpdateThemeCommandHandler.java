package ec.diners.com.application.commands.theme.update;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.themeDetail.update.UpdateThemeDetailCommand;
import ec.diners.com.application.commands.themeDetail.register.CreateThemeDetailCommand;
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
public class UpdateThemeCommandHandler implements Command.Handler<UpdateThemeCommand, Response<UpdateThemeResponse>> {
    private static final Logger logger = LoggerFactory.getLogger(UpdateThemeCommandHandler.class);
    private final IThemeRepository themeRepository;
    private final Pipeline mediator;

    public UpdateThemeCommandHandler(IThemeRepository themeRepository, Pipeline mediator) {
        this.themeRepository = themeRepository;
        this.mediator = mediator;
    }

    @Transactional
    @Override
    public Response<UpdateThemeResponse> handle(UpdateThemeCommand updateThemeCommand) {
        var processResponse = new ProcessResponse<UpdateThemeResponse>();

        //buscamos el id del tema para actualizarlo
        var objTheme = themeRepository.findById(updateThemeCommand.getId());
        logger.info("VALOR DEL ID:{}", Objects.nonNull(objTheme) ? objTheme.getId() : null );

        if (objTheme != null) {
            objTheme.setId(objTheme.getId());
            objTheme.setName(updateThemeCommand.getName());
            objTheme.setDescription(updateThemeCommand.getDescription());
            objTheme.setUserUpdate(updateThemeCommand.getUpdateUser());
            objTheme.setUpdatedAt(new Date());

            themeRepository.update(objTheme);
            // actualizamos el detalle
            updateThemeCommand.getDetailTheme().forEach(themeDetail->{
                if(themeDetail.getId() != null){
                    UpdateThemeDetailCommand command = new UpdateThemeDetailCommand();
                    command.setThemeId(themeDetail.getThemeId());
                    command.setName(themeDetail.getName());
                    command.setValue(themeDetail.getValue());
                    command.setId(themeDetail.getId());
                    this.mediator.send(command);
                }else{
                    CreateThemeDetailCommand command = new CreateThemeDetailCommand();
                    command.setThemeId(themeDetail.getThemeId());
                    command.setName(themeDetail.getName());
                    command.setValue(themeDetail.getValue());
                    this.mediator.send(command);
                }
            });
        }else{
            return processResponse.error(new Response<>(new UpdateThemeResponse(-1L)));
        }
        return processResponse.success(new UpdateThemeResponse(objTheme.getId()));
    }
}
