package ec.diners.com.application.queries.themeProduct.getAllProductUnassigned;


import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.themeProduct.CardProductResponse;
import ec.diners.com.domain.response.Response;

import java.util.List;

public record GetCardProductsNotInProductsThemeQuery() implements Command<Response<List<CardProductResponse>>> {
}
