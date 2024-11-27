package ec.diners.com.application.commands.themePartner.reset;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.partnerIntegration.IPartnerIntegrationRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class ResetPartnerCommandHandler implements Command.Handler<ResetPartnerCommand, Response<Boolean>> {

    private final IPartnerIntegrationRepository partnerRepository;

    public ResetPartnerCommandHandler(IPartnerIntegrationRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Response<Boolean> handle(ResetPartnerCommand resetPartnerCommand) {
        var processResponse = new ProcessResponse<Boolean>();
        var objPartner = partnerRepository.findByDinersId(resetPartnerCommand.identificationNumber());

        if (objPartner == null){
            return processResponse.error("Error.: el usuario no existe o esta de baja.: "+resetPartnerCommand.identificationNumber());
        }
        //se le quita la asignacion de tema que tiene asignado el usuario
        objPartner.setThemeId(null);
        partnerRepository.update(objPartner);

        return processResponse.success(Boolean.TRUE);
    }
}
