package ec.diners.com.application.commands.user.authenticate;

import java.util.Date;

public record AuthenticateUserCommandResponse(
        String accessToken,
        String refreshToken,
        Date accessExpiresIn,
        Date refreshExpiresIn
) {
}
