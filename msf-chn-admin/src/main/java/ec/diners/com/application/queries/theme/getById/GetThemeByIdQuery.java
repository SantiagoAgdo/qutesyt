package ec.diners.com.application.queries.theme.getById;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.response.Response;


public record GetThemeByIdQuery(Long themeId) implements Command<Response<Theme>> {

}
