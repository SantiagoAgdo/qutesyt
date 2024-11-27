package ec.diners.com.application.commands.user.authenticate;

import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class AuthenticateUserCommandValidator implements CommandValidator<AuthenticateUserCommand, Response<AuthenticateUserCommandResponse>> {
    @Override
    public void validate(AuthenticateUserCommand command) throws ValidationException {
        var validationErrors = new ArrayList<String>();

        if (command.email().isEmpty()){
            validationErrors.add("Email cannot be empty");
        }

        String password = command.password();

        if (password.isEmpty()|| Objects.isNull(password)){
            validationErrors.add("Password can not be null or empty");
        }
        if (password.length() < 8) {
            validationErrors.add("Password must be at least 8 characters long");
        }

        if (!validationErrors.isEmpty()){
            var message = StringUtils.join(validationErrors, ", ");
            throw new ValidationException(message);
        }
    }
}
