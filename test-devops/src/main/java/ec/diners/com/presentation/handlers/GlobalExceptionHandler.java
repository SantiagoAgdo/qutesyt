package ec.diners.com.presentation.handlers;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.constants.LogType;
import ec.diners.com.domain.response.ErrorResponse;
import ec.diners.com.domain.services.entityLog.systemLog.CreateEntityLogSystemService;
import ec.diners.com.domain.utils.Utils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Environment environment;
    private final Pipeline mediator;
    private final Utils utils;

    public GlobalExceptionHandler(Environment environment, Pipeline mediator, Utils utils) {
        this.environment = environment;
        this.mediator = mediator;
        this.utils = utils;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ResponseEntity<ErrorResponse> handleGenericError(Exception e) {
        mediator.send(new CreateEntityLogSystemService(
                LogType.ERROR,
                String.format("Exception: %s", utils.readException(e)))
        );

        var response = new ErrorResponse();

        if (e.getMessage().contains("ValidationException")){
            response.setMessage(e.getMessage().substring(38));
            response.setDetails(e.getMessage().substring(38));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if(e.getMessage().contains("Access is denied")) {
            response.setMessage("Access denied");
            response.setDetails("Access denied");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if(e.getMessage().contains("JWT expired") ||
                e.getMessage().contains("AuthenticationFailedException") ||
                e.getMessage().contains("BadCredentialsException")) {
            response.setMessage("There are authentication errors.");
            response.setDetails("There are authentication errors.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        var profiles = environment.getActiveProfiles();
        if (profiles.length > 0 && profiles[0].equals("dev")) {
            response.setMessage("Some errors were found");
            response.setDetails("Some errors were found");
        }
        else {
            response.setMessage(utils.readException(e));
            response.setDetails(e.getMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
