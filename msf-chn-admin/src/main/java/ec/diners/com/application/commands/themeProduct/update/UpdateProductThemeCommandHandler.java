package ec.diners.com.application.commands.themeProduct.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.personalityChange.IPersonalityChangeRepository;
import ec.diners.com.domain.entities.theme.Theme;
import ec.diners.com.domain.entities.themeProduct.ProductTheme;
import ec.diners.com.domain.interfaces.repositories.theme.IThemeRepository;
import ec.diners.com.domain.interfaces.repositories.themeProduct.IProductThemeRepository;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.infrastructure.modelsDb.personality.PersonalizationChangeModel;
import ec.diners.com.infrastructure.utils.TaskPersonalityNameEnum;
import ec.diners.com.infrastructure.utils.TaskPersonalityStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UpdateProductThemeCommandHandler implements Command.Handler<UpdateProductThemeCommand, Response<Long>> {

    private final IProductThemeRepository productThemeRepository;
    private final IThemeRepository themeRepository;
    private final IPersonalityChangeRepository personalityChangeRepository;

    public UpdateProductThemeCommandHandler(IProductThemeRepository productThemeRepository, IThemeRepository themeRepository, IPersonalityChangeRepository personalityChangeRepository) {
        this.productThemeRepository = productThemeRepository;
        this.themeRepository = themeRepository;
        this.personalityChangeRepository = personalityChangeRepository;
    }

    @Override
    public Response<Long> handle(UpdateProductThemeCommand updateProductThemeCommand) {
        var processResponse = new ProcessResponse<Long>();
        Theme theme = null;
        Long result = null;
        PersonalizationChangeModel personalizationChangeModel = null;

        theme = this.themeRepository.findById(updateProductThemeCommand.getThemeId());

        if(theme == null){
            return processResponse.error("Theme no existente en la bd id "+updateProductThemeCommand.getThemeId());
        }

        ProductTheme productTheme = this.productThemeRepository.findById(updateProductThemeCommand.getProductThemeId());

        if(productTheme == null){
            return processResponse.error("Theme-producto Id no existente en la bd "+updateProductThemeCommand.getProductThemeId());
        }

        ProductTheme productThemeDb = new ProductTheme();
        productThemeDb.setId(updateProductThemeCommand.getProductThemeId());
        productThemeDb.setThemeId(updateProductThemeCommand.getThemeId());
        productThemeDb.setCodeProduct(productTheme.getCodeProduct());
        productThemeDb.setCreatorUserId(productTheme.getCreatorUserId());
        productThemeDb.setIsActive(productTheme.getIsActive());
        productThemeDb.setPriority(productTheme.getPriority());
        productThemeDb.setUpdaterUserId(updateProductThemeCommand.getUser());
        productThemeDb.setCreatedAt(productTheme.getCreatedAt());
        result = this.productThemeRepository.update(productThemeDb);

        //create personality_changes
        personalizationChangeModel = new PersonalizationChangeModel();
        personalizationChangeModel.setState(TaskPersonalityStatusEnum.PENDING.getValue());
        personalizationChangeModel.setName(TaskPersonalityNameEnum.UPDATE_PRODUCT_THEME.getValue());
        personalizationChangeModel.setCode(String.join(",", List.of(productTheme.getCodeProduct()).stream().map(Object::toString).toArray(String[]::new)));
        personalityChangeRepository.save(personalizationChangeModel);

        return processResponse.success(result);
    }
}
