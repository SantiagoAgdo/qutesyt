package ec.diners.com.application.commands.themeProduct.register;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.themeProduct.ProductThemeRequest;
import ec.diners.com.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductThemeCommand extends ProductThemeRequest implements Command<Response<Boolean>> {
    public CreateProductThemeCommand() {
        super();
    }
}
