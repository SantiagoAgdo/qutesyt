package ec.diners.com.application.queries.themeDetail.getByName;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.theme.ThemeDetailsView;
import ec.diners.com.domain.response.Response;

import java.util.List;

public record GetThemeDetailsByNameQuery(Long themeId) implements Command<Response<List<ThemeDetailsView>>> {

}
