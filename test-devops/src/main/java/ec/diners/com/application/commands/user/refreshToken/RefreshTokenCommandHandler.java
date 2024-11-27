package ec.diners.com.application.commands.user.refreshToken;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.constants.LoginFieldConstants;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.security.UserAuthTokenRepository;
import ec.diners.com.domain.interfaces.repositories.user.UserRepository;
import ec.diners.com.domain.interfaces.security.JwtToken;
import ec.diners.com.domain.response.ErrorResponse;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.services.security.userAuthToken.getByRefreshToken.GetUserAuthTokenByRefreshTokenService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static ec.diners.com.application.utils.UserAuthenticationUtils.generateToken;

@Component
@Transactional
public class RefreshTokenCommandHandler implements Command.Handler<RefreshTokenCommand, Response<RefreshTokenCommandResponse>> {

    private final UserAuthTokenRepository userAuthTokenRepository;
    private final UserRepository userRepository;
    private final Pipeline mediator;
    @Value("${token.access-expires-in-min}")
    private int tokenAccessExpiresIn;

    public RefreshTokenCommandHandler(UserAuthTokenRepository userAuthTokenRepository, UserRepository userRepository, Pipeline mediator) {
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.userRepository = userRepository;
        this.mediator = mediator;
    }


    @Override
    public Response<RefreshTokenCommandResponse> handle(RefreshTokenCommand command) {
        var processResponse = new ProcessResponse<RefreshTokenCommandResponse>();

        var userAuthTokenResponse = mediator.send(new GetUserAuthTokenByRefreshTokenService(command.refreshToken()));

        if (!userAuthTokenResponse.isSuccess()) {
            return processResponse.error(new ErrorResponse("Unauthorized Error", "User Not Authenticated"));
        }

        var accessExpiresIn = DateUtils.addMinutes(new Date(), tokenAccessExpiresIn);
        var userAuthTokenResponseValue = userAuthTokenResponse.getValue();
        var userId = userAuthTokenResponseValue.getUserId();
        var user = userRepository.getById(userId);

        var accessToken = generateToken(user, accessExpiresIn);
        userAuthTokenResponseValue.setAccessToken(accessToken);
        userAuthTokenResponseValue.setAccessTokenExpiresIn(accessExpiresIn);
        userAuthTokenRepository.update(userAuthTokenResponseValue);

        return processResponse.success(
                new RefreshTokenCommandResponse(
                        userAuthTokenResponseValue.getAccessToken(),
                        userAuthTokenResponseValue.getRefreshToken(),
                        accessExpiresIn,
                        userAuthTokenResponseValue.getRefreshTokenExpiresIn()));

    }


}
