package ec.diners.com.application.commands.user.refreshToken;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record RefreshTokenCommand(String refreshToken) implements Command<Response<RefreshTokenCommandResponse>> {

}
