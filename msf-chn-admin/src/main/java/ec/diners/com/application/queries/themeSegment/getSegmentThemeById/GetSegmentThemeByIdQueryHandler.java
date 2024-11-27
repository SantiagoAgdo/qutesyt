package ec.diners.com.application.queries.themeSegment.getSegmentThemeById;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.themeSegment.SegmentTheme;
import ec.diners.com.domain.interfaces.repositories.themeSegment.ISegmentThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.springframework.stereotype.Component;

@Component
public class GetSegmentThemeByIdQueryHandler implements Command.Handler<GetSegmentThemeByIdQuery, Response<SegmentTheme>> {

    private final ISegmentThemeRepository segmentThemeRepository;

    public GetSegmentThemeByIdQueryHandler(ISegmentThemeRepository segmentThemeRepository) {
        this.segmentThemeRepository = segmentThemeRepository;
    }

    @Override
    public Response<SegmentTheme> handle(GetSegmentThemeByIdQuery getSegmentThemeByIdQuery) {
        var processResponse = new ProcessResponse<SegmentTheme>();
        SegmentTheme result = segmentThemeRepository.findById(getSegmentThemeByIdQuery.id());
        if (result == null){
            return processResponse.error("La configuracion de tema con id no existente configuracion en la bd. "+getSegmentThemeByIdQuery.id());
        }
        return processResponse.success(result);
    }
}
