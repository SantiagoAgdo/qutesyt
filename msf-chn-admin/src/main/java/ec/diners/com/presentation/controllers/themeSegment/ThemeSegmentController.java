package ec.diners.com.presentation.controllers.themeSegment;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.themeSegment.register.CreateSegmentThemeCommand;
import ec.diners.com.application.commands.themeSegment.update.UpdateSegmentThemeCommand;
import ec.diners.com.application.dtos.request.themeSegment.SegmentThemeRequest;
import ec.diners.com.application.dtos.request.themeSegment.UpdateSegmentThemeRequest;
import ec.diners.com.application.queries.themeSegment.getAllSegmentTheme.GetAllSegmentThemeQuery;
import ec.diners.com.application.queries.themeSegment.getSegmentThemeById.GetSegmentThemeByIdQuery;
import ec.diners.com.presentation.controllers.BaseController;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/segmentThemeAdmin")
public class ThemeSegmentController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeSegmentController.class);
    private final Pipeline mediator;

    public ThemeSegmentController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    public ResponseEntity<Object> getAllSegmentTheme() {
        logger.info("Init getAllSegmentTheme");
        var query = new GetAllSegmentThemeQuery();
        var response = mediator.send(query);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/getSegmentThemeById/{id}")
    public ResponseEntity<Object> getSegmentThemeById(@PathVariable("id") Long id) {
        logger.info("Init getSegmentThemeById {}", id);
        var query = new GetSegmentThemeByIdQuery(id);
        var response = mediator.send(query);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createSegmentTheme(@Valid @RequestBody List<SegmentThemeRequest> request) {
        var command = new CreateSegmentThemeCommand();
        command.setLstSegmentTheme(request);
        var response = mediator.send(command);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateSegmentTheme(@Valid @RequestBody List<UpdateSegmentThemeRequest> request) {
        var command = new UpdateSegmentThemeCommand();
        command.setLstSegmentTheme(request);
        var response = mediator.send(command);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

}
