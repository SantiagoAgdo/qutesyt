package ec.diners.com.application.commands.themeDetail.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeDetailsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class UpdateThemeDetailCommandHandler implements Command.Handler<UpdateThemeDetailCommand, Void> {
    private final IThemeDetailsRepository themeDetailsRepository;

    public UpdateThemeDetailCommandHandler(IThemeDetailsRepository themeDetailsRepository) {
        this.themeDetailsRepository = themeDetailsRepository;
    }

    @Transactional
    @Override
    public Void handle(UpdateThemeDetailCommand updateThemeDetailCommand) {
        //validamos que el id exista para actualizar
        var objThemeDetail = themeDetailsRepository.findById(updateThemeDetailCommand.getId());
        if (objThemeDetail != null) {
            objThemeDetail.setThemeId(updateThemeDetailCommand.getThemeId());
            objThemeDetail.setName(updateThemeDetailCommand.getName());
            objThemeDetail.setValue(updateThemeDetailCommand.getValue());
            themeDetailsRepository.update(objThemeDetail);
        }
        return null;
    }
}
