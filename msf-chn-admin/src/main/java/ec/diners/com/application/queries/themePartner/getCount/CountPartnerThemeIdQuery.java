package ec.diners.com.application.queries.themePartner.getCount;


import an.awesome.pipelinr.Command;
import ec.diners.com.domain.response.Response;

public record CountPartnerThemeIdQuery(Long themeId) implements Command<Response<Long>> {

}
