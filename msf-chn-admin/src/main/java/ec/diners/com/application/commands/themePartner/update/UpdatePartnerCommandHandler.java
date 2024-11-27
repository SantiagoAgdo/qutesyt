package ec.diners.com.application.commands.themePartner.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class UpdatePartnerCommandHandler implements Command.Handler<UpdatePartnerCommand, Response<Boolean>> {

    private final IPartnerIntegrationRepository partnerRepository;

    public UpdatePartnerCommandHandler(IPartnerIntegrationRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Response<Boolean> handle(UpdatePartnerCommand updatePartnerCommand) {
        var processResponse = new ProcessResponse<Boolean>();
        //buscamos si el dinersId existe
        var objPartner = partnerRepository.findByDinersId(updatePartnerCommand.identificationNumber());

        if(objPartner == null){
            return processResponse.error("Error.: el usuario no existe o esta de baja.: "+updatePartnerCommand.identificationNumber());
        }
        objPartner.setThemeId(updatePartnerCommand.themeId());
        partnerRepository.update(objPartner);

        return processResponse.success(Boolean.TRUE);
    }
}
