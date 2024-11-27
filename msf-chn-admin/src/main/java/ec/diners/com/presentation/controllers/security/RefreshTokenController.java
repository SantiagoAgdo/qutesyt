package ec.diners.com.presentation.controllers.security;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.user.refreshToken.RefreshTokenCommand;
import ec.diners.com.domain.models.GetEntitiesResponse;
import ec.diners.com.presentation.controllers.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController extends BaseController {

    private final Pipeline mediator;

    public RefreshTokenController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @PutMapping
    @Operation(summary = "Refresh auth user", description = "Authenticate user credentials", responses = {
            @ApiResponse(responseCode = "200", description = "Refresh successful", content = @Content(schema = @Schema(implementation = GetEntitiesResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    public ResponseEntity<Object> refreshToken(@RequestBody RefreshTokenCommand command) {

        var sanitizedCommand = new RefreshTokenCommand(sanitizeString(command.refreshToken()));
        var response = mediator.send(sanitizedCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }
}
