package ec.diners.com.application.commands.theme.register;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.themeDetail.register.CreateThemeDetailCommand;
import ec.diners.com.application.dtos.response.theme.CreateThemeResponse;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CreateThemeCommandHandler implements Command.Handler<CreateThemeCommand, Response<CreateThemeResponse>> {

    private final IThemeRepository themeRepository;
    private final Pipeline mediator;

    public CreateThemeCommandHandler(IThemeRepository themeRepository, Pipeline mediator) {
        this.themeRepository = themeRepository;
        this.mediator = mediator;
    }


    @Override
    public Response<CreateThemeResponse> handle(CreateThemeCommand createThemeCommand) {
        var processResponse = new ProcessResponse<CreateThemeResponse>();

        //validamos que el nombre del tema no este registrado
        var objTheme = themeRepository.findByName(createThemeCommand.getName());
        if (objTheme != null) {
            return processResponse.error("Theme ya existente en la bd.");
        }
        Theme theme = new Theme();
        theme.setName(createThemeCommand.getName());
        theme.setDescription(createThemeCommand.getDescription());
        theme.setUserCreate(createThemeCommand.getUserCreate());
        Long themeId = themeRepository.save(theme);

        //guardamos el detalle
        createThemeCommand.getDetailTheme().forEach(themeDetails->{
            CreateThemeDetailCommand command= new CreateThemeDetailCommand();
            command.setThemeId(themeId);
            command.setName(themeDetails.getName());
            command.setValue(themeDetails.getValue());
            this.mediator.send(command);
        });
        var response = new CreateThemeResponse(themeId);
        return processResponse.success(response);
    }
}
