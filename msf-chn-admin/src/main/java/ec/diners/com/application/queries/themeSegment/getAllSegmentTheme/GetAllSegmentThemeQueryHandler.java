package ec.diners.com.application.queries.themeSegment.getAllSegmentTheme;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.entities.themeSegment.SegmentThemeListAllView;
import ec.diners.com.domain.interfaces.repositories.themeSegment.ISegmentThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllSegmentThemeQueryHandler implements Command.Handler<GetAllSegmentThemeQuery, Response<List<SegmentThemeListAllView>>> {

    private final ISegmentThemeRepository segmentThemeRepository;

    public GetAllSegmentThemeQueryHandler(ISegmentThemeRepository segmentThemeRepository) {
        this.segmentThemeRepository = segmentThemeRepository;
    }

    @Override
    public Response<List<SegmentThemeListAllView>> handle(GetAllSegmentThemeQuery getAllSegmentThemeQuery) {
        var processResponse = new ProcessResponse<List<SegmentThemeListAllView>>();
        List<SegmentThemeListAllView> result = segmentThemeRepository.findAllRecordsActives();

        if (CollectionUtils.isEmpty(result)){
            return processResponse.error("Segment-Theme no existente configuracion en la bd.");
        }
        return processResponse.success(result);
    }
}
