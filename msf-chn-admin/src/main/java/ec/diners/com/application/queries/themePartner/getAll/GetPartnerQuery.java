package ec.diners.com.application.queries.themePartner.getAll;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.response.themePartner.PartnerThemeView;
import ec.diners.com.domain.response.Response;

import java.util.List;

public record GetPartnerQuery() implements Command<Response<List<PartnerThemeView>>> {
}
