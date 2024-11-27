package ec.diners.com.presentation.middlewares;

import an.awesome.pipelinr.Command;
import ec.diners.com.infrastructure.interfaces.CommandValidator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import jakarta.validation.ValidationException;

@Component
public class CommandValidationMiddleware implements Command.Middleware {
    private final ObjectProvider<CommandValidator> validators;

    CommandValidationMiddleware(ObjectProvider<CommandValidator> validators) {
        this.validators = validators;
    }

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        validators.stream().filter(v -> v.matches(command)).findFirst().ifPresent(v -> {
            try {
                v.validate(command);
            } catch (Exception e) {
                throw new ValidationException(e);
            }
        });
        return next.invoke();
    }
}
