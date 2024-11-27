package ec.diners.com.application.commands.themeDetail.register;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.theme.CreateThemeResponse;
import ec.diners.com.domain.entities.theme.ThemeDetails;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeDetailsRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateThemeDetailCommandHandler implements Command.Handler<CreateThemeDetailCommand, Response<CreateThemeResponse>> {
    private final IThemeDetailsRepository themeDetailsRepository;

    public CreateThemeDetailCommandHandler(IThemeDetailsRepository themeDetailsRepository) {
        this.themeDetailsRepository = themeDetailsRepository;
    }

    @Transactional
    @Override
    public Response<CreateThemeResponse>  handle(CreateThemeDetailCommand createThemeDetailCommand) {
        var processResponse = new ProcessResponse<CreateThemeResponse>();

        ThemeDetails themeDetails = new ThemeDetails();
        themeDetails.setThemeId(createThemeDetailCommand.getThemeId());
        themeDetails.setName(createThemeDetailCommand.getName());
        themeDetails.setValue(createThemeDetailCommand.getValue());

        Long themeDetailsId = themeDetailsRepository.save(themeDetails);

        var response = new CreateThemeResponse(themeDetailsId);
        return processResponse.success(response);
    }
}
