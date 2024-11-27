package ec.diners.com.application.commands.user.refreshToken;

import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class RefreshTokenCommandValidator implements CommandValidator<RefreshTokenCommand, Response<RefreshTokenCommandResponse>> {

    @Override
    public void validate(RefreshTokenCommand command) throws ValidationException {
        var validationErrors = new ArrayList<String>();

        if (command.refreshToken() == null){
            validationErrors.add("refreshToken cannot be empty");
        }

        if (!validationErrors.isEmpty()){
            var message = StringUtils.join(validationErrors, ", ");
            throw new ValidationException(message);
        }
    }
}
