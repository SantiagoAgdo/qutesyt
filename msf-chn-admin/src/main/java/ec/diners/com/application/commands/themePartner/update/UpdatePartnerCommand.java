package ec.diners.com.application.commands.themePartner.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record UpdatePartnerCommand(String identificationNumber, Long themeId) implements Command<Response<Boolean>> {

}
