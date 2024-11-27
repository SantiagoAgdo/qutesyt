package ec.diners.com.application.queries.themeDetail.getByName;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.theme.ThemeDetailsView;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeDetailsRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetThemeDetailsByNameQueryHandler implements Command.Handler<GetThemeDetailsByNameQuery, Response<List<ThemeDetailsView>>> {

    private static final Logger logger = LoggerFactory.getLogger(GetThemeDetailsByNameQueryHandler.class);
    private final IThemeDetailsRepository themeDetailsRepository;

    public GetThemeDetailsByNameQueryHandler(IThemeDetailsRepository themeDetailsRepository) {
        this.themeDetailsRepository = themeDetailsRepository;
    }


    @Override
    public Response<List<ThemeDetailsView>> handle(GetThemeDetailsByNameQuery getThemeDetailsByNameQuery) {
        var processResponse = new ProcessResponse<List<ThemeDetailsView>>();
        List<ThemeDetailsView> lstThemeDetail= null;
        logger.info("**** Init GetThemeDetailsByNameQuery by id:{} ****", getThemeDetailsByNameQuery.themeId() );
        lstThemeDetail = themeDetailsRepository.getThemeDetailsView(getThemeDetailsByNameQuery.themeId());
        if(CollectionUtils.isEmpty(lstThemeDetail)){
            return processResponse.error("Theme Deatils no existente en la bd. id->"+getThemeDetailsByNameQuery.themeId());
        }
        return processResponse.success(lstThemeDetail);
    }
}
