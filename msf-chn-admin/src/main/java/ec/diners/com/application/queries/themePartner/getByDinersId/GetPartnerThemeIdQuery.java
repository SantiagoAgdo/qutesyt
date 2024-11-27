package ec.diners.com.application.queries.themePartner.getByDinersId;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record GetPartnerThemeIdQuery(String dinersId) implements Command<Response<Long>> {

}
