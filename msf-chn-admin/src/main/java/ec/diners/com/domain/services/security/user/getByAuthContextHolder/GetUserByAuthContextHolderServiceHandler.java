package ec.diners.com.domain.services.security.user.getByAuthContextHolder;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.user.base.UserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetUserByAuthContextHolderServiceHandler implements Command.Handler<GetUserByAuthContextHolderService, Response<User>>{

    private final UserRepository userRepository;

    public GetUserByAuthContextHolderServiceHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response<User> handle(GetUserByAuthContextHolderService service) {
        var processResponse = new ProcessResponse<User>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser userAuthToken =null;

        if (authentication != null && authentication.getPrincipal() instanceof LoginUser) {
            userAuthToken = (LoginUser) authentication.getPrincipal();
        }
        if (userAuthToken == null || userAuthToken.getId() == null) {
            return processResponse.error("User ID not found in token");
        }

        var userResponse = userRepository.getById(userAuthToken.getId());
        if(userResponse==null){
            return processResponse.error("User can not be found");
        }

        return processResponse.success(userResponse);
    }
}