package ec.diners.com.domain.services.security.userAuthToken.getByRefreshToken;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.domain.response.Response;


public record GetUserAuthTokenByRefreshTokenService(String refreshToken)
        implements Command<Response<UserAuthToken>> {

}
