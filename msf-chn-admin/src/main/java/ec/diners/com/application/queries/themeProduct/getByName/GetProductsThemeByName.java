package ec.diners.com.application.queries.themeProduct.getByName;


import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.themeProduct.ProductThemeResponse;
import ec.diners.com.domain.response.Response;

import java.util.List;

public record GetProductsThemeByName(String name) implements Command<Response<List<ProductThemeResponse>>> {

}
