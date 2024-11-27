package ec.diners.com.domain.services.security.user.getByEmail;

import an.awesome.pipelinr.Command;
import de.mobiuscode.nameof.Name;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.user.base.UserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterOperator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetUserByEmailServiceHandler implements Command.Handler<GetUserByEmailService, Response<User>>{

    private final UserRepository userRepository;

    public GetUserByEmailServiceHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Response<User> handle(GetUserByEmailService service) {
        var processResponse = new ProcessResponse<User>();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(User.class, User::getEmail), FilterOperator.EQ, service.email()));
        var criteria = new Criteria(filters);

        var user = userRepository.getFirst(criteria);
        if (user == null){
            return processResponse.error("Entity not found");
        }

        return processResponse.success(user);
    }
}
