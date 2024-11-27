package ec.diners.com.domain.exceptions;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Entity for Exception Domain
 * @author Opinno
 * @version 1.0
 */

@Getter
public class DomainException extends RuntimeException{

    private static final Logger logger_ = LoggerFactory.getLogger(DomainException.class);
    private final HttpStatus httpStatus;
    private String internalMessage;

    public DomainException(String message, HttpStatus httpStatus)
    {
        super(message);
        this.httpStatus = httpStatus;
    }

    public DomainException(String message, final String internalMessage,
                           HttpStatus httpStatus)
    {
        super(message);
        this.internalMessage = internalMessage;
        this.httpStatus = httpStatus;
    }

    public DomainException(String message, HttpStatus httpStatus, Exception innerException)
    {
        super(message, innerException);
        this.httpStatus = httpStatus;
    }

    public DomainException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;


    }

    public DomainException(String message, final String internalMessage) {

        super(message);
        this.internalMessage = internalMessage;
        this.httpStatus = HttpStatus.NOT_FOUND;

    }
    
}
