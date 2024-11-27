package ec.diners.com.application.queries.themePartner.getAll;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.themePartner.PartnerThemeView;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GetPartnerQueryHandler implements Command.Handler<GetPartnerQuery, Response<List<PartnerThemeView>>> {
    private static final Logger logger = LoggerFactory.getLogger(GetPartnerQueryHandler.class);
    private final IPartnerIntegrationRepository partnerRepository;

    public GetPartnerQueryHandler(IPartnerIntegrationRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Response<List<PartnerThemeView>> handle(GetPartnerQuery getPartnerQuery) {
        var processResponse = new ProcessResponse<List<PartnerThemeView>>();
        logger.info("--Query list partner all config partner-integration with themeId--");
        List<PartnerThemeView> response = partnerRepository.listPartner();
        if (CollectionUtils.isEmpty(response)){
            return processResponse.error("Listado de Partner-Theme no existe registros bd.");
        }
        return processResponse.success(response);
    }
}
