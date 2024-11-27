package ec.diners.com.domain.services.security.user.getByAuthContextHolder;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.response.Response;

public record GetUserByAuthContextHolderService() implements Command<Response<User>> {

}
