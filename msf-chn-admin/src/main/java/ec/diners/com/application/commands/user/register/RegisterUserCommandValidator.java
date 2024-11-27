package ec.diners.com.application.commands.user.register;

//import ec.com.blubancaback.admin.domain.constants.AuthenticateConstants;

import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Objects;

@Component
public class RegisterUserCommandValidator implements CommandValidator<RegisterUserCommand, Response<RegisterUserCommandResponse>> {
    @Override
    public void validate(RegisterUserCommand command) throws ValidationException {
        var validationErrors = new ArrayList<String>();

        if (command.email().isEmpty()) {
            validationErrors.add("Email cannot be empty");
        }

        String password = command.password();

        if (password.isEmpty() || Objects.isNull(password)) {
            validationErrors.add("Password can not be null or empty");
        }
        if (password.length() < 8) {
            validationErrors.add("Password must be at least 8 characters long");
        }
        if (command.name().isEmpty() || command.name() == null) {
            validationErrors.add("Name cannot be empty or null");
        }

        if (command.lastname().isEmpty() || command.lastname() == null) {
            validationErrors.add("Lastname cannot be empty or null");
        }



       /* if (!AuthenticateConstants.UPPERCASE_PATTERN.matcher(password).find()) {
            validationErrors.add("Password must contain at least one uppercase letter");
        }

        if (!AuthenticateConstants.LOWERCASE_PATTERN.matcher(password).find()) {
            validationErrors.add("Password must contain at least one lowercase letter");
        }

        if (!AuthenticateConstants.DIGIT_PATTERN.matcher(password).find()) {
            validationErrors.add("Password must contain at least one digit");
        }


        */
        if (!validationErrors.isEmpty()) {
            var message = StringUtils.join(validationErrors, ", ");
            throw new ValidationException(message);
        }
    }
}
