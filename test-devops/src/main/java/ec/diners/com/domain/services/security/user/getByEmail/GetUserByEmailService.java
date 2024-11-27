package ec.diners.com.domain.services.security.user.getByEmail;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.response.Response;


public record GetUserByEmailService(String email) implements Command<Response<User>> {

}
