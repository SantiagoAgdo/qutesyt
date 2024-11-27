package ec.diners.com.domain.services.security.userAuthToken.getExpiredList;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.security.UserAuthToken;
import ec.diners.com.domain.response.Response;

import java.util.List;


public record GetUserTokenExpiredListService() implements Command<Response<List<UserAuthToken>>> {
}
