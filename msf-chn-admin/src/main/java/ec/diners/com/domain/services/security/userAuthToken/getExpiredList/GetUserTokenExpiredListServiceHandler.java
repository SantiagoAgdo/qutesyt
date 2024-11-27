package ec.diners.com.domain.services.security.userAuthToken.getExpiredList;

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
import java.util.Date;
import java.util.List;

@Component
public class GetUserTokenExpiredListServiceHandler implements Command.Handler<GetUserTokenExpiredListService, Response<List<UserAuthToken>>>{

    private final UserAuthTokenRepository repository;

    public GetUserTokenExpiredListServiceHandler(UserAuthTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response<List<UserAuthToken>> handle(GetUserTokenExpiredListService service) {
        var processResponse = new ProcessResponse<List<UserAuthToken>>();

        var list = new ArrayList<UserAuthToken>();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(UserAuthToken.class, UserAuthToken::getRefreshTokenExpiresIn), FilterOperator.LT, new Date()));

        var criteria = new Criteria(filters, Integer.MAX_VALUE);

        var getResponse = repository.getAll(criteria);
        if (getResponse.getEntities() == null || getResponse.getEntities().isEmpty()) {
            return processResponse.success(list);
        }

        var presentEntity = getResponse.getEntities().stream().findFirst();
        if (presentEntity.isEmpty()) {
            return processResponse.error("Entity not found");
        }

        list = new ArrayList<>(getResponse.getEntities());
        return processResponse.success(list);
    }
}
