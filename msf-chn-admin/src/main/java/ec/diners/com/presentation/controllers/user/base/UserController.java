package ec.diners.com.presentation.controllers.user.base;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.user.authenticate.AuthenticateUserCommand;
import ec.diners.com.application.commands.user.authenticate.AuthenticateUserCommandResponse;
import ec.diners.com.application.commands.user.register.RegisterUserCommand;
import ec.diners.com.application.commands.user.register.RegisterUserCommandResponse;
import ec.diners.com.application.queries.user.getById.GetUserByIdQuery;
import ec.diners.com.application.queries.user.getById.GetUserByIdQueryResponseModel;
import ec.diners.com.presentation.controllers.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final Pipeline mediator;

    public UserController(Pipeline mediator) {
        this.mediator = mediator;
    }


    @PostMapping("/register")
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RegisterUserCommandResponse.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerBearerAuthentication"))
    public ResponseEntity<Object> registerBackOfficeUser(@RequestBody RegisterUserCommand command) {

        var sanitizedCommand = new RegisterUserCommand(
                sanitizeString(command.email()),
                sanitizeString(command.name()),
                sanitizeString(command.lastname()),
                sanitizeString(command.password())
        );
        var response = mediator.send(sanitizedCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);

    }

    @PostMapping("/login")
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AuthenticateUserCommandResponse.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerBearerAuthentication"))
    public ResponseEntity<Object> authenticateUser(@RequestBody AuthenticateUserCommand command) {

        var sanitizedCommand = new AuthenticateUserCommand(
                sanitizeString(command.email()),
                sanitizeString(command.password())
        );

        var response = mediator.send(sanitizedCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);

    }

    @GetMapping
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetUserByIdQueryResponseModel.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerBearerAuthentication"))
    public ResponseEntity<Object> getById(@RequestParam UUID userId) {

        var sanitizedCommand = new GetUserByIdQuery(sanitizeId(userId));
        var response = mediator.send(sanitizedCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);

        }

        return createResponse(response.getValue(), HttpStatus.OK);

    }


}
