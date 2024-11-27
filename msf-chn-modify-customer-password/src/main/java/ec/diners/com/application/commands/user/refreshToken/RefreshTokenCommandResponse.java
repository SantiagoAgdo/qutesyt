package ec.diners.com.application.commands.user.refreshToken;


import java.util.Date;

public record RefreshTokenCommandResponse(String accessToken, String refreshToken, Date accessExpiresIn,
                                          Date refreshExpiresIn) {
}
