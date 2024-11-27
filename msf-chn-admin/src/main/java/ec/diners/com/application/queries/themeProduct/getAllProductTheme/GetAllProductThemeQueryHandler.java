package ec.diners.com.application.queries.themeProduct.getAllProductTheme;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.themeProduct.ProductThemeListAllView;
import ec.diners.com.domain.interfaces.repositories.themeProduct.IProductThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllProductThemeQueryHandler implements Command.Handler<GetAllProductThemeQuery, Response<List<ProductThemeListAllView>>> {

    private final IProductThemeRepository productThemeRepository;

    public GetAllProductThemeQueryHandler(IProductThemeRepository productThemeRepository) {
        this.productThemeRepository = productThemeRepository;
    }

    @Override
    public Response<List<ProductThemeListAllView>> handle(GetAllProductThemeQuery getAllProductThemeQuery) {
        var processResponse = new ProcessResponse<List<ProductThemeListAllView>>();
        List<ProductThemeListAllView> result = this.productThemeRepository.findAll();
        if (CollectionUtils.isEmpty(result)){
            return processResponse.error("List Product-Theme no existente configuracion bd.");
        }
        return processResponse.success(result);
    }
}
