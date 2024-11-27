package ec.diners.com.application.queries.themePartner.getByDinersId;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class GetPartnerThemeIdQueryHandler implements Command.Handler<GetPartnerThemeIdQuery, Response<Long>> {

    private final IPartnerIntegrationRepository partnerRepository;

    public GetPartnerThemeIdQueryHandler(IPartnerIntegrationRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }


    @Override
    public Response<Long> handle(GetPartnerThemeIdQuery getPartnerThemeIdQuery) {
        var processResponse = new ProcessResponse<Long>();
        var result = partnerRepository.findByDinersId(getPartnerThemeIdQuery.dinersId());

        if (result == null){
            return processResponse.error("No existe partner con el dinersId "+getPartnerThemeIdQuery.dinersId());
        }
        return processResponse.success(result.getId());
    }
}