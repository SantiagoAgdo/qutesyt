package ec.diners.com.domain.services.security.userAuthToken.getByRefreshToken;

import an.awesome.pipelinr.Command;
import de.mobiuscode.nameof.Name;
import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.domain.interfaces.repositories.security.UserAuthTokenRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterOperator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetUserAuthTokenByRefreshTokenServiceHandler implements Command.Handler<GetUserAuthTokenByRefreshTokenService, Response<UserAuthToken>>{

    private final UserAuthTokenRepository repository;

    public GetUserAuthTokenByRefreshTokenServiceHandler(UserAuthTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<UserAuthToken> handle(GetUserAuthTokenByRefreshTokenService service) {
        var processResponse = new ProcessResponse<UserAuthToken>();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(UserAuthToken.class,UserAuthToken::getRefreshToken), FilterOperator.EQ, service.refreshToken()));
        var criteria = new Criteria(filters);

        var user = repository.getFirst(criteria);
        if (user == null) {
            return processResponse.error("Entity not found");
        }

        return processResponse.success(user);
    }
}
