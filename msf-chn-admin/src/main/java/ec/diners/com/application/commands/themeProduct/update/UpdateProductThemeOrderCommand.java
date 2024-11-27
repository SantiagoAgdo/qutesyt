package ec.diners.com.application.commands.themeProduct.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.themeProduct.ProductThemeOrderRequest;
import ec.diners.com.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductThemeOrderCommand extends ProductThemeOrderRequest implements Command<Response<Boolean>> {
}
