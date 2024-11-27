package ec.diners.com.application.commands.themeProduct.delete;


import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record DeleteProductThemeCommandById(Long id) implements Command<Response<Boolean>> {

}
