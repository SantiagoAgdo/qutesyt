package ec.diners.com.application.queries.themeProduct.getAllProductUnassigned;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.themeProduct.CardProductResponse;
import ec.diners.com.domain.interfaces.repositories.cardProduct.ICardProductRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCardProductsNotInProductsThemeQueryHandler implements Command.Handler<GetCardProductsNotInProductsThemeQuery, Response<List<CardProductResponse>>> {

    private final ICardProductRepository cardProductRepository;

    public GetCardProductsNotInProductsThemeQueryHandler(ICardProductRepository cardProductRepository) {
        this.cardProductRepository = cardProductRepository;
    }

    @Override
    public Response<List<CardProductResponse>> handle(GetCardProductsNotInProductsThemeQuery getCardProductsNotInProductsThemeQuery) {
        var processResponse = new ProcessResponse<List<CardProductResponse>>();
        List<CardProductResponse> result = null;
        result = this.cardProductRepository.findAllRecordsNotInProductTheme().stream().map(item -> new CardProductResponse(item.getCodeProduct(), item.getNameProduct(), item.getDescriptionProduct())).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(result)){
            return processResponse.error("CardProducts-NotIn-ProductsTheme no result record.");
        }
        return processResponse.success(result);
    }
}
