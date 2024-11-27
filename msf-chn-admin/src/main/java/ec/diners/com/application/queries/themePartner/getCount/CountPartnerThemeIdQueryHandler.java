package ec.diners.com.application.queries.themePartner.getCount;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class CountPartnerThemeIdQueryHandler implements Command.Handler<CountPartnerThemeIdQuery, Response<Long>> {

    private final IPartnerIntegrationRepository partnerRepository;

    public CountPartnerThemeIdQueryHandler(IPartnerIntegrationRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Response<Long> handle( CountPartnerThemeIdQuery validateThemeIdQuery) {

        var processResponse = new ProcessResponse<Long>();
        var resultCount = partnerRepository.findThemeId(validateThemeIdQuery.themeId());
        if (resultCount == null){
            return processResponse.error("Count Partner/ThemeId no tiene resultados.");
        }
        return processResponse.success(resultCount);
    }
}
