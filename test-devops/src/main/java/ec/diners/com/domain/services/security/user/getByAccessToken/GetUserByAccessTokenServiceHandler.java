package ec.diners.com.domain.services.security.user.getByAccessToken;

import an.awesome.pipelinr.Command;
import de.mobiuscode.nameof.Name;
import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.security.UserAuthTokenRepository;
import ec.diners.com.domain.interfaces.repositories.user.UserRepository;
import ec.diners.com.domain.response.ErrorResponse;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterOperator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetUserByAccessTokenServiceHandler implements Command.Handler<GetUserByAccessTokenService, Response<User>>{

    private final UserAuthTokenRepository userAuthTokenRepository;
    private final UserRepository userRepository;

    public GetUserByAccessTokenServiceHandler(UserAuthTokenRepository userAuthTokenRepository, UserRepository userRepository) {
        this.userAuthTokenRepository = userAuthTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Response<User> handle(GetUserByAccessTokenService service) {
        var processResponse = new ProcessResponse<User>();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(UserAuthToken.class,UserAuthToken::getAccessToken), FilterOperator.EQ, service.accessToken()));

        var criteria = new Criteria(filters);

        var userAuthToken = userAuthTokenRepository.getFirst(criteria);
        if (userAuthToken == null) {
            return processResponse.error(new ErrorResponse("Unauthorized Error", "User Not Authenticated"));
        }

        var user = userRepository.getById(userAuthToken.getUserId());
        if (user == null) {
            return processResponse.error(new ErrorResponse("Unauthorized Error", "User Not Authenticated"));
        }

        return processResponse.success(user);
    }
}
