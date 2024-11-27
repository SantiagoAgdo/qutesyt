package ec.diners.com.application.queries.themeProduct.getAllProductTheme;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.themeProduct.ProductThemeListAllView;
import ec.diners.com.domain.response.Response;

import java.util.List;

public record GetAllProductThemeQuery() implements Command<Response<List<ProductThemeListAllView>>> {
}
