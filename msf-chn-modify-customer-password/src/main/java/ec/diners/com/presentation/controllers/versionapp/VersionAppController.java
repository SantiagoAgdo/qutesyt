package ec.diners.com.presentation.controllers.versionapp;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.queries.versionapp.GetCurrentVersionAppQuery;
import ec.diners.com.application.queries.versionapp.GetCurrentVersionAppQueryResponseModel;
import ec.diners.com.presentation.controllers.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/versions-app")
public class VersionAppController extends BaseController {

    private final Pipeline mediator;

    public VersionAppController(Pipeline mediator) {
        this.mediator = mediator;
    }


    @GetMapping("/current-version")
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetCurrentVersionAppQueryResponseModel.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerBearerAuthentication"))
    public ResponseEntity<Object> getCurrentVersion() {

        var response = mediator.send(new GetCurrentVersionAppQuery());

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);

        }

        return createResponse(response.getValue(), HttpStatus.OK);

    }
}
