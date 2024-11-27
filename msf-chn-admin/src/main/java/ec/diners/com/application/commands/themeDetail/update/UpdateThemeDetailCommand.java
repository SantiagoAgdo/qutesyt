package ec.diners.com.application.commands.themeDetail.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.application.dtos.request.theme.UpdateThemeDetailRequest;

public class UpdateThemeDetailCommand extends UpdateThemeDetailRequest implements Command<Void> {
    public UpdateThemeDetailCommand() {
        super();
    }
}
