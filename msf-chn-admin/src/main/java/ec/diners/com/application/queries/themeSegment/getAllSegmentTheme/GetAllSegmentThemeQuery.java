package ec.diners.com.application.queries.themeSegment.getAllSegmentTheme;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.themeSegment.SegmentThemeListAllView;
import ec.diners.com.domain.response.Response;
import java.util.List;


public record GetAllSegmentThemeQuery() implements Command<Response<List<SegmentThemeListAllView>>> {
}
