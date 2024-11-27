package ec.diners.com.domain.services.security.user.getByAccessToken;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.response.Response;


public record GetUserByAccessTokenService(String accessToken) implements Command<Response<User>> {

}
