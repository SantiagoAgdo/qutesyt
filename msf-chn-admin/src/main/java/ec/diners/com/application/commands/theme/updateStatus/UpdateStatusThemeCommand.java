package ec.diners.com.application.commands.theme.updateStatus;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.theme.UpdateStatusThemeRequest;
import ec.diners.com.application.dtos.response.theme.UpdateThemeResponse;
import ec.diners.com.domain.response.Response;

public class UpdateStatusThemeCommand extends UpdateStatusThemeRequest implements Command<Response<UpdateThemeResponse>> {
    public UpdateStatusThemeCommand() {
        super();
    }
}
