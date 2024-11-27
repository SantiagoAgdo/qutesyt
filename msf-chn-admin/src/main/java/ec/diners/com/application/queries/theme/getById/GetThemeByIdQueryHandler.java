package ec.diners.com.application.queries.theme.getById;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GetThemeByIdQueryHandler implements Command.Handler<GetThemeByIdQuery, Response<Theme>> {

    private final IThemeRepository themeRepository;

    private static final Logger logger = LoggerFactory.getLogger(GetThemeByIdQueryHandler.class);

    public GetThemeByIdQueryHandler(IThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Response<Theme> handle(GetThemeByIdQuery getThemeByIdQuery) {
        var processResponse = new ProcessResponse<Theme>();
        var objTheme = themeRepository.findById(getThemeByIdQuery.themeId());
        if (objTheme == null){
            return processResponse.error("Theme no existente en la bd. id->"+getThemeByIdQuery.themeId());
        }
        return processResponse.success(objTheme);
    }
}
