package ec.diners.com.presentation.controllers.theme;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.theme.register.CreateThemeCommand;
import ec.diners.com.application.commands.theme.updateStatus.UpdateStatusThemeCommand;
import ec.diners.com.application.commands.theme.update.UpdateThemeCommand;
import ec.diners.com.application.dtos.request.theme.CreateThemeRequest;
import ec.diners.com.application.dtos.request.theme.UpdateStatusThemeRequest;
import ec.diners.com.application.dtos.request.theme.UpdateThemeRequest;
import ec.diners.com.application.dtos.response.theme.CreateThemeResponse;
import ec.diners.com.application.queries.theme.getById.GetThemeByIdQuery;
import ec.diners.com.application.queries.themeDetail.getByName.GetThemeDetailsByNameQuery;
import ec.diners.com.application.queries.theme.list.ListThemeQuery;
import ec.diners.com.presentation.controllers.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/theme")
public class ThemeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeController.class);
    private final Pipeline mediator;

    public ThemeController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/getGreeting/{name}")
    public ResponseEntity<String> getGreeting(@PathVariable("name") String name){
        logger.info("**** HOLA MUNDO Info **** {}", name);
        return new ResponseEntity<>("**** Hola Mundo ****..."+name, HttpStatus.OK);
    }

    @GetMapping("/listThemes")
    public ResponseEntity<Object> getTheme() {
        var query = new ListThemeQuery();
        var response = mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/getThemeDetailById/{themeId}")
    public ResponseEntity<Object> getThemeDetailById(@PathVariable(required = true) Long themeId) {
        logger.info("Init getThemeDetailByName by id {}", themeId);
        var query = new GetThemeDetailsByNameQuery(themeId);
        var response = mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/getTheme/{themeId}")
    public ResponseEntity<Object> getThemeById(@PathVariable(required = true) Long themeId) {
        var query = new GetThemeByIdQuery(themeId);
        var response = mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PostMapping("/createTheme")
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CreateThemeResponse.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerBearerAuthentication"))
    public ResponseEntity<Object> createThemeApp(@RequestBody CreateThemeRequest request) {
        logger.info("Create theme  {}", request.getClass().getName(), request);
        var safeCommand= new CreateThemeCommand();
        safeCommand.setName(sanitizeString(request.getName().trim()));
        safeCommand.setDescription(sanitizeString(request.getDescription()));
        safeCommand.setDetailTheme(request.getDetailTheme());
        safeCommand.setUserCreate(request.getUserCreate());
        var response = mediator.send(safeCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping("/updateTheme")
    public ResponseEntity<Object> updateThemeApp(@RequestBody UpdateThemeRequest request) {
        logger.info("Update theme {}", request.getClass().getName(), request);
        var safeCommand= new UpdateThemeCommand();
        safeCommand.setId(request.getId());
        safeCommand.setName(sanitizeString(request.getName().trim()));
        safeCommand.setDescription(sanitizeString(request.getDescription()));
        safeCommand.setDetailTheme(request.getDetailTheme());
        safeCommand.setUpdateUser(request.getUpdateUser());
        var response = mediator.send(safeCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping("/updateStatusTheme")
    public ResponseEntity<Object> updateStatusThemeApp(@RequestBody UpdateStatusThemeRequest request) {
        logger.info("Api rest/updateStatusTheme {}", request.getClass().getName());
        var safeCommand= new UpdateStatusThemeCommand();
        safeCommand.setId(request.getId());
        safeCommand.setUpdateUser(request.getUpdateUser());
        safeCommand.setIsActive(request.getIsActive());
        var response = mediator.send(safeCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

}
