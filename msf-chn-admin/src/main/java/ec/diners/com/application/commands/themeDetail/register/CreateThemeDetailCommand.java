package ec.diners.com.application.commands.themeDetail.register;


import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.theme.CreateDetailThemeRequest;
import ec.diners.com.application.dtos.response.theme.CreateThemeResponse;
import ec.diners.com.domain.response.Response;

public class CreateThemeDetailCommand extends CreateDetailThemeRequest implements Command<Response<CreateThemeResponse>> {

    public CreateThemeDetailCommand() {
        super();
    }

}

