package ec.diners.com.application.commands.user.login;

import java.util.Date;

public record AuthenticateUserCommandResponse(
        String accessToken,
        String refreshToken,
        Date accessExpiresIn,
        Date refreshExpiresIn
) {
}
