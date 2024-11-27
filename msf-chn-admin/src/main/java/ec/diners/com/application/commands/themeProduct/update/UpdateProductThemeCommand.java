package ec.diners.com.application.commands.themeProduct.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.themeProduct.UpdateProductThemeRequest;
import ec.diners.com.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductThemeCommand extends UpdateProductThemeRequest implements Command<Response<Long>> {
}
