package ec.diners.com.application.commands.user.authenticate;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.domain.interfaces.repositories.security.UserAuthTokenRepository;
import ec.diners.com.domain.interfaces.repositories.user.base.UserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.services.security.user.getByEmail.GetUserByEmailService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

import static ec.diners.com.application.utils.UserAuthenticationUtils.generateRefreshToken;
import static ec.diners.com.application.utils.UserAuthenticationUtils.generateToken;

@Component
@Transactional
public class AuthenticateUserCommandHandler implements Command.Handler<AuthenticateUserCommand, Response<AuthenticateUserCommandResponse>> {

    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final UserAuthTokenRepository userAuthTokenRepository;

    private final Pipeline mediator;

    @Value("${token.access-expires-in-min}")
    private int tokenAccessExpiresIn;
    @Value("${token.refresh-expires-in-min}")
    private int tokenRefreshExpiresIn;

    public AuthenticateUserCommandHandler(UserAuthTokenRepository userAuthTokenRepository, Pipeline mediator) {
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.mediator = mediator;
        this.cryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Response<AuthenticateUserCommandResponse> handle(AuthenticateUserCommand command) {
        var processResponse = new ProcessResponse<AuthenticateUserCommandResponse>();

        var email = command.email();

        var userResponse = mediator.send(new GetUserByEmailService(email));

        if (!userResponse.isSuccess()) {
            return processResponse.error(userResponse);
        }
        var user = userResponse.getValue();

        if (!user.getIsActive()) {
            return processResponse.error("User account is locked");
        }

        try {
            if (!cryptPasswordEncoder.matches(command.password(), user.getPassword())) {
                return processResponse.error("Incorrect username or password");
            }
        } catch (IllegalArgumentException e) {
            return processResponse.error("Invalid password format");
        }

        if (!user.getIsConfirmed()) {
            return processResponse.error("User isn't confirmed. Please confirm your account");
        }

        var accessExpiresIn = DateUtils.addMinutes(new Date(), tokenAccessExpiresIn);
        var refreshExpiresIn = DateUtils.addMinutes(new Date(), tokenRefreshExpiresIn);

        var accessToken = generateToken(user, accessExpiresIn);
        var refreshToken = generateRefreshToken(user, refreshExpiresIn);

        var userAuthToken = new UserAuthToken();
        userAuthToken.setAccessToken(accessToken);
        userAuthToken.setRefreshToken(refreshToken);
        userAuthToken.setAccessTokenExpiresIn(accessExpiresIn);
        userAuthToken.setRefreshTokenExpiresIn(refreshExpiresIn);
        userAuthToken.setUser(user);
        userAuthTokenRepository.add(userAuthToken);

        var response = new AuthenticateUserCommandResponse(accessToken, refreshToken, accessExpiresIn, refreshExpiresIn);
        return processResponse.success(response);

    }

}
