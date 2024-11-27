package ec.diners.com.presentation.controllers.user;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.user.password.PasswordChangeCommand;
import ec.diners.com.application.commands.user.password.PasswordChangeCommandResponse;
import ec.diners.com.domain.response.OperationResponse;
import ec.diners.com.presentation.controllers.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/password")
@RequiredArgsConstructor
public class PasswordController extends BaseController {

    private final Pipeline mediator;


    @PostMapping("/modify")
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PasswordChangeCommandResponse.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerPasswordChange"))
    public ResponseEntity<Object> modifyPassword(@RequestBody PasswordChangeCommand command) {
        return Optional.of(command)
                .map(this::sanitizeCommand)
                .map(this::processCommand)
                .map(this::createResponseEntity)
                .orElse(ResponseEntity.internalServerError().build());
    }

    private PasswordChangeCommand sanitizeCommand(PasswordChangeCommand command) {
        return new PasswordChangeCommand(
                sanitizeDinHeader(command.dinHeader()),
                sanitizeDinBody(command.dinBody())
        );
    }

    private PasswordChangeCommand.PasswordChangeHeaderCommand sanitizeDinHeader(PasswordChangeCommand.PasswordChangeHeaderCommand header) {
        return new PasswordChangeCommand.PasswordChangeHeaderCommand(
                sanitizeString(header.aplicacionId()),
                sanitizeString(header.canalId()),
                sanitizeString(header.uuid()),
                sanitizeString(header.sesionId()),
                sanitizeString(header.portalId()),
                sanitizeString(header.ip()),
                sanitizeString(header.horaTransaccion()),
                sanitizeString(header.nivelTrace()),
                sanitizeString(header.nombreServicio()),
                sanitizeString(header.llaveSimetrica()),
                sanitizePaginado(header.paginado()),
                sanitizeString(header.usuario())
        );
    }

    private PasswordChangeCommand.PaginationCommand sanitizePaginado(PasswordChangeCommand.PaginationCommand paginado) {
        return new PasswordChangeCommand.PaginationCommand(
                paginado.cantRegistros(),
                paginado.numTotalPag(),
                paginado.numPagActual()
        );
    }

    private PasswordChangeCommand.PasswordChangeBodyCommand sanitizeDinBody(PasswordChangeCommand.PasswordChangeBodyCommand body) {
        return new PasswordChangeCommand.PasswordChangeBodyCommand(
                sanitizeString(body.clave()),
                sanitizeString(body.nuevaClave()),
                sanitizeString(body.usuarioBiometrico()),
                sanitizeString(body.tipoIngreso()),
                sanitizeString(body.perfil()),
                sanitizeString(body.codigoUnicoAplicacion())
        );
    }

    private OperationResponse<?> processCommand(PasswordChangeCommand sanitizedCommand) {
        try {
            var response = mediator.send(sanitizedCommand);
            return response.isSuccess()
                    ? OperationResponse.success(response.getValue())
                    : OperationResponse.error(response.getErrorResponse().getMessage());
        } catch (Exception e) {
            return OperationResponse.error("Server internal error");
        }
    }

    private ResponseEntity<Object> createResponseEntity(OperationResponse<?> response) {
        return response.isSuccess()
                ? createResponse(response.value(), HttpStatus.OK)
                : createResponse(response, HttpStatus.BAD_REQUEST) ;
    }

}
