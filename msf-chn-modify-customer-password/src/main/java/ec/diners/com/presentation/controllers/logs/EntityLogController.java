package ec.diners.com.presentation.controllers.logs;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.queries.entityLog.getAll.GetEntityLogsQuery;
import ec.diners.com.domain.models.GetEntitiesResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entity-logs")
public class EntityLogController extends BaseController {

    private final Pipeline mediator;

    public EntityLogController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    @RouterOperation(operation = @Operation(responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetEntitiesResponse.class)))))
    @Operation(summary = "", security = @SecurityRequirement(name = "SwaggerBearerAuthentication"))
    public ResponseEntity<Object> get(@RequestParam(value = "filters", required = false) List<String> filters,
                                      @RequestParam(value = "orders", required = false) List<String> orders,
                                      @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "0") int pageSize) {

        var filterPagination = getFilterPaginationQueryModel(filters, orders, page, pageSize);
        var response = mediator.send(new GetEntityLogsQuery(filterPagination));

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);

        }

        return createResponse(response.getValue(), HttpStatus.OK);


    }
}
