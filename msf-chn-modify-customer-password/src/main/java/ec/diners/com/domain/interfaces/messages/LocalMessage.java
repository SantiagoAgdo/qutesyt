package ec.diners.com.domain.interfaces.messages;

public interface LocalMessage {
    String getMessage(String keyMessage);
    String getMessage(String keyMessage, String[] args);
}
