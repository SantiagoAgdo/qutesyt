package ec.diners.com.application.commands.themeSegment.update;


import an.awesome.pipelinr.Command;

import ec.diners.com.application.dtos.request.themeSegment.UpdateSegmentThemeRequest;
import ec.diners.com.domain.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateSegmentThemeCommand  implements Command<Response<Boolean>> {

    private List<UpdateSegmentThemeRequest> lstSegmentTheme;

    public UpdateSegmentThemeCommand() {
    }
}
