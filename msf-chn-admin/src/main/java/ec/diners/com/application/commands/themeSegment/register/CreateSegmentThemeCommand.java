package ec.diners.com.application.commands.themeSegment.register;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.themeSegment.SegmentThemeRequest;
import ec.diners.com.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateSegmentThemeCommand implements Command<Response<Boolean>> {

    private List<SegmentThemeRequest> lstSegmentTheme;

    public CreateSegmentThemeCommand() {
    }
}
