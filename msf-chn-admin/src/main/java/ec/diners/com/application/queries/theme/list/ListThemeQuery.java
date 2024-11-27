package ec.diners.com.application.queries.theme.list;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.response.Response;

import java.util.List;

public record ListThemeQuery() implements Command<Response<List<Theme>>> {
}
