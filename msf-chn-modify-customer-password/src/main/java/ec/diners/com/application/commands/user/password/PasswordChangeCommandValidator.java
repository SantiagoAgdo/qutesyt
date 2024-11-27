package ec.diners.com.application.commands.user.password;

import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PasswordChangeCommandValidator implements CommandValidator<PasswordChangeCommand, Response<PasswordChangeCommandResponse>> {

    @Override
    public void validate(PasswordChangeCommand command) throws ValidationException {
        var validationErrors = new ArrayList<String>();

        if (command.dinBody().usuarioBiometrico().isEmpty()) {
            validationErrors.add("UsuarioBiometrico cannot be empty");
        }

        if (!validationErrors.isEmpty()) {
            var message = StringUtils.join(validationErrors, ", ");
            throw new ValidationException(message);
        }
    }
}
