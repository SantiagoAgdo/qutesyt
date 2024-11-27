package ec.diners.com.presentation.controllers.themeProduct;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.application.commands.themeProduct.delete.DeleteProductThemeCommandById;
import ec.diners.com.application.commands.themeProduct.register.CreateProductThemeCommand;
import ec.diners.com.application.commands.themeProduct.update.UpdateProductThemeCommand;
import ec.diners.com.application.commands.themeProduct.update.UpdateProductThemeOrderCommand;
import ec.diners.com.application.dtos.request.themeProduct.ProductThemeOrderRequest;
import ec.diners.com.application.dtos.request.themeProduct.ProductThemeRequest;
import ec.diners.com.application.dtos.request.themeProduct.UpdateProductThemeRequest;
import ec.diners.com.application.queries.themeProduct.getAllProductTheme.GetAllProductThemeQuery;
import ec.diners.com.application.queries.themeProduct.getAllProductUnassigned.GetCardProductsNotInProductsThemeQuery;
import ec.diners.com.application.queries.themeProduct.getByName.GetProductsThemeByName;
import ec.diners.com.presentation.controllers.BaseController;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productThemeAdmin")
public class ThemeProductController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ThemeProductController.class);
    private final Pipeline mediator;

    public ThemeProductController(Pipeline mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/getProductByName")
    public ResponseEntity<Object> getProductByName(@RequestParam(value = "name", required = false) String name) {
        logger.info("Init getProductsThemeByFilters");
        var query = new GetProductsThemeByName(sanitizeString(name));
        var response = mediator.send(query);
        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveProductTheme(@Valid @RequestBody ProductThemeRequest request){
        logger.info("***** Init saveProductTheme *****");
        var query = new CreateProductThemeCommand();
        query.setCodeProductList(request.getCodeProductList());
        query.setThemeId(request.getThemeId());
        query.setUser(sanitizeString(request.getUser()));
        var response = this.mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductThemeById(@PathVariable("id") Long id) {
        logger.info("*** Init deleteProductThemeById: {} ***", id);
        var query = new DeleteProductThemeCommandById(id);
        var response = this.mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAllProductsTheme() {
        logger.info("Init getAllProductsTheme");
        var query = new GetAllProductThemeQuery();
        var response = this.mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductTheme(@PathVariable("id") Long id, @Valid @RequestBody UpdateProductThemeRequest request){
        logger.info("*** Init updateProductTheme: {} ***", id);
        var query = new UpdateProductThemeCommand();
        query.setProductThemeId(id);
        query.setThemeId(request.getThemeId());
        query.setUser(sanitizeString(request.getUser()));
        var response = this.mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @PutMapping("/order")
    public ResponseEntity<Object> updateOrderProductTheme(@Valid @RequestBody ProductThemeOrderRequest request){
        var query = new UpdateProductThemeOrderCommand();
        query.setIdProductList(request.getIdProductList());
        var response = this.mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

    @GetMapping("/getAllProductUnassigned")
    public ResponseEntity<Object> getCardProductsNotInProductsThemeQuery() {
        logger.info("Init getCardProductsNotInProductsThemeQuery");
        var query = new GetCardProductsNotInProductsThemeQuery();
        var response = this.mediator.send(query);

        if (!response.isSuccess()) {
            return createResponse(response.getErrorResponse(), HttpStatus.BAD_REQUEST);
        }
        return createResponse(response.getValue(), HttpStatus.OK);
    }

}
