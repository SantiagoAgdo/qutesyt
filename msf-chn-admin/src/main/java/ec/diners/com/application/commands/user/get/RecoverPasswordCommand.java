package ec.diners.com.application.commands.user.get;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.user.RecoverPasswordRequest;
import ec.diners.com.application.dtos.response.user.RecoverUserResponse;
import ec.diners.com.domain.response.Response;

public class RecoverPasswordCommand extends RecoverPasswordRequest implements Command<Response<RecoverUserResponse>> {

    public RecoverPasswordCommand() {
        super();
    }
}