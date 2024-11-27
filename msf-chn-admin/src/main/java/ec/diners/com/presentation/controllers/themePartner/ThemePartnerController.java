package ec.diners.com.presentation.controllers.themePartner;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.themePartner.reset.ResetPartnerCommand;
import ec.diners.com.application.commands.themePartner.update.UpdatePartnerCommand;
import ec.diners.com.application.dtos.request.themePartner.ResetPartnerRequest;
import ec.diners.com.application.dtos.request.themePartner.UpdatePartnerThemeRequest;
import ec.diners.com.application.queries.themePartner.getAll.GetPartnerQuery;
import ec.diners.com.application.queries.themePartner.getByDinersId.GetPartnerThemeIdQuery;
import ec.diners.com.application.queries.themePartner.getCount.CountPartnerThemeIdQuery;
import ec.diners.com.domain.entities.personality.PersonalizationValidationDto;
import ec.diners.com.domain.interfaces.services.personality.IAsyncThemePersonalityService;
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
@RequestMapping("/partnerThemeAdmin")
public class ThemePartnerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ThemePartnerController.class);
    private final IAsyncThemePersonalityService asyncThemePersonalityService;
    private final Pipeline mediator;

    public ThemePartnerController(IAsyncThemePersonalityService asyncThemePersonalityService, Pipeline mediator) {
        this.asyncThemePersonalityService = asyncThemePersonalityService;
        this.mediator = mediator;
    }

    @GetMapping("/listPartner")
    public ResponseEntity<Object> listPartner(){
        var query = new GetPartnerQuery();
        var response = mediator.send(query);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping("/updatePartner")
    public ResponseEntity<Object> updatePartner(@Valid @RequestBody UpdatePartnerThemeRequest request) {
        var command = new UpdatePartnerCommand(request.getIdentificationNumber(), request.getThemeId());
        var response = mediator.send(command);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        var updateTheme = new PersonalizationValidationDto();
        updateTheme.setLstPartners(List.of(request.getIdentificationNumber()));

        this.asyncThemePersonalityService.validationThemesByPriority(updateTheme);

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/countPartnerTheme/{themeId}")
    public ResponseEntity<Object> getPartnerThemeId(@PathVariable Long themeId) {
        logger.info("*** Count partners by ThemeId partnerIntegration ***{}", themeId);
        var command = new CountPartnerThemeIdQuery(themeId);
        var response = mediator.send(command);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/getPartnerThemeId/{dinersId}")
    public ResponseEntity<Object> getPartnerThemeIdByDinersId(@PathVariable String dinersId) {
        logger.info("*** DinersId request ***{}", dinersId);
        var command = new GetPartnerThemeIdQuery(sanitizeString(dinersId));
        var response = mediator.send(command);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping("/resetPartner")
    public ResponseEntity<Object> resetPartner(@Valid @RequestBody ResetPartnerRequest request){
        logger.info("*** DinersId request ***{}", request.getIdentificationNumber());
        var objCommand = new ResetPartnerCommand(request.getIdentificationNumber());
        var response = mediator.send(objCommand);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }

        var updateTheme = new PersonalizationValidationDto();
        updateTheme.setLstPartners(List.of(request.getIdentificationNumber()));
        this.asyncThemePersonalityService.validationThemesByPriority(updateTheme);

        return createResponse(response.getValue(), HttpStatus.OK);
    }

}
