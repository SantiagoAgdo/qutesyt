package ec.diners.com.infrastructure.messages;

import ec.diners.com.domain.interfaces.messages.LocalMessage;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocalMessageImpl implements LocalMessage {

    private final MessageSource messageSource;
    private final Locale locale;

    public LocalMessageImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.getDefault();
    }

    @Override
    public String getMessage(String keyMessage) {
        return messageSource.getMessage(keyMessage, null, "MessageKey not found", locale);
    }

    @Override
    public String getMessage(String keyMessage, String[] args) {
        return messageSource.getMessage(keyMessage, args, "MessageKey not found", locale);
    }
}
