package ec.diners.com.application.queries.themeSegment.getSegmentThemeById;


import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.domain.response.Response;

public record GetSegmentThemeByIdQuery(Long id) implements Command<Response<SegmentTheme>> {

}
