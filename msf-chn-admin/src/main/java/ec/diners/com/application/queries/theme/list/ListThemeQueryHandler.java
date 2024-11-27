package ec.diners.com.application.queries.theme.list;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListThemeQueryHandler implements Command.Handler<ListThemeQuery, Response<List<Theme>>> {
    private final IThemeRepository themeRepository;

    public ListThemeQueryHandler(IThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Response<List<Theme>> handle(ListThemeQuery listThemeCommand) {
        var processResponse = new ProcessResponse<List<Theme>>();
        return processResponse.success(themeRepository.listTheme());
    }
}
