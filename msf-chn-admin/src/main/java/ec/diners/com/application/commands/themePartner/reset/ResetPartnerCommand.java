package ec.diners.com.application.commands.themePartner.reset;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record ResetPartnerCommand(String identificationNumber) implements Command<Response<Boolean>> {
}
