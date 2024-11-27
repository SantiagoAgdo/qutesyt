package ec.diners.com.application.queries.themeProduct.getByName;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.themeProduct.ProductThemeResponse;
import ec.diners.com.domain.interfaces.repositories.cardProduct.ICardProductRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetProductsThemeByNameHandler implements Command.Handler<GetProductsThemeByName, Response<List<ProductThemeResponse>>> {

    private final ICardProductRepository cardProductRepository;

    public GetProductsThemeByNameHandler(ICardProductRepository cardProductRepository) {
        this.cardProductRepository = cardProductRepository;
    }

    @Override
    public Response<List<ProductThemeResponse>> handle(GetProductsThemeByName getProductsThemeByName) {
        var processResponse = new ProcessResponse<List<ProductThemeResponse>>();
        List<ProductThemeResponse> response = null;
        if (StringUtils.isBlank(getProductsThemeByName.name())) {
            response = cardProductRepository.findAll()
                    .stream().map(item -> new ProductThemeResponse(item.getCodeProduct(), item.getNameProduct(), item.getDescriptionProduct())).collect(Collectors.toList());
        } else {
            response = cardProductRepository.findByName(getProductsThemeByName.name())
                    .stream().map(item -> new ProductThemeResponse(item.getCodeProduct(), item.getNameProduct(), item.getDescriptionProduct())).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(response)){
            return processResponse.error("Product-Theme no existente configuracion bd");
        }
        return processResponse.success(response);
    }
}