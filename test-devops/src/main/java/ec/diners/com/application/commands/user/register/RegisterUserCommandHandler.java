package ec.diners.com.application.commands.user.register;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import de.mobiuscode.nameof.Name;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.repositories.user.UserRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.specification.Criteria;
import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterConcat;
import ec.diners.com.domain.specification.FilterOperator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.util.ArrayList;

@Component
@Transactional
public class RegisterUserCommandHandler implements Command.Handler<RegisterUserCommand, Response<RegisterUserCommandResponse>> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;
    private final Pipeline mediator;

    public RegisterUserCommandHandler(UserRepository userRepository, BCryptPasswordEncoder cryptPasswordEncoder, Pipeline mediator) {
        this.userRepository = userRepository;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
        this.mediator = mediator;
    }


    @Override
    public Response<RegisterUserCommandResponse> handle(RegisterUserCommand command) {
        var processResponse = new ProcessResponse<RegisterUserCommandResponse>();


        var email = command.email();

        var filters = new ArrayList<Filter>();
        filters.add(new Filter(Name.of(User.class, User::getIsActive), FilterOperator.EQ, true, FilterConcat.AND));
        filters.add(new Filter(Name.of(User.class, User::getEmail), FilterOperator.EQ, email));

        var criteria = new Criteria(filters);

        var user = userRepository.getFirst(criteria);

        if (user != null && user.getIsConfirmed()) {
            return processResponse.error("User with same email already exist");
        }


        var encodedPassword = cryptPasswordEncoder.encode(command.password());

        var newUser = new User();
        newUser.setEmail(command.email());
        newUser.setPassword(encodedPassword);
        newUser.setIsActive(true);
        newUser.setIsConfirmed(false);
        newUser.setName(command.name());
        newUser.setLastname(command.lastname());

        newUser = userRepository.add(newUser);

        if (newUser.getId() == null) {
            return processResponse.error("User creation failed");
        }

        var response = new RegisterUserCommandResponse("User pending confirmation with OTP. Please view your email account", Boolean.TRUE);
        return processResponse.success(response);

    }


}