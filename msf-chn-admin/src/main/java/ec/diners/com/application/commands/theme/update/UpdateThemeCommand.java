package ec.diners.com.application.commands.theme.update;


import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.theme.UpdateThemeRequest;
import ec.diners.com.application.dtos.response.theme.UpdateThemeResponse;
import ec.diners.com.domain.response.Response;

public class UpdateThemeCommand extends UpdateThemeRequest implements Command<Response<UpdateThemeResponse>> {
    public UpdateThemeCommand() {
        super();
    }
}
