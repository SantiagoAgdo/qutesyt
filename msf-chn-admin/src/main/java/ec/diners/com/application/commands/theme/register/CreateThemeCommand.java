package ec.diners.com.application.commands.theme.register;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.theme.CreateThemeRequest;
import ec.diners.com.application.dtos.response.theme.CreateThemeResponse;
import ec.diners.com.domain.response.Response;

public class CreateThemeCommand extends CreateThemeRequest implements Command<Response<CreateThemeResponse>> {

    public CreateThemeCommand() {
        super();
    }

}
