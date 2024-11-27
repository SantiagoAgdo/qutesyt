package ec.diners.com.infrastructure.interfaces;

import an.awesome.pipelinr.Command;
import com.google.common.reflect.TypeToken;
import jakarta.validation.ValidationException;

public interface CommandValidator<C extends Command<R>, R> {
    void validate(C command) throws ValidationException;

    default boolean matches(C command) {
        TypeToken<C> typeToken = new TypeToken<C>(getClass()) {
        };

        return typeToken.isSupertypeOf(command.getClass());
    }
}
