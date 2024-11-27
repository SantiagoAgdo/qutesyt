package ec.diners.com.application.commands.themeProduct.delete;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.personalityChange.IPersonalityChangeRepository;
import ec.diners.com.domain.entities.themeProduct.ProductTheme;
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
public class DeleteProductThemeCommandByIdHandler implements Command.Handler<DeleteProductThemeCommandById, Response<Boolean>> {

    private final IProductThemeRepository productThemeRepository;
    private final IPersonalityChangeRepository personalityChangeRepository;

    public DeleteProductThemeCommandByIdHandler(IProductThemeRepository productThemeRepository, IPersonalityChangeRepository personalityChangeRepository) {
        this.productThemeRepository = productThemeRepository;
        this.personalityChangeRepository = personalityChangeRepository;
    }

    @Override
    public Response<Boolean> handle(DeleteProductThemeCommandById deleteProductThemeCommandById) {
        var processResponse = new ProcessResponse<Boolean>();
        ProductTheme productTheme = null;
        PersonalizationChangeModel personalizationChangeModel = null;

        productTheme = this.productThemeRepository.findById(deleteProductThemeCommandById.id());

        if (productTheme == null) {
            return processResponse.error("Producto - Theme con el id no existe en la bd." + deleteProductThemeCommandById.id());
        }

        this.productThemeRepository.deleteById(deleteProductThemeCommandById.id());

        //create personality_changes
        personalizationChangeModel = new PersonalizationChangeModel();
        personalizationChangeModel.setState(TaskPersonalityStatusEnum.PENDING.getValue());
        personalizationChangeModel.setName(TaskPersonalityNameEnum.DELETE_PRODUCT_THEME.getValue());
        personalizationChangeModel.setCode(String.join(",", List.of(productTheme.getCodeProduct()).stream().map(Object::toString).toArray(String[]::new)));
        personalityChangeRepository.save(personalizationChangeModel);

        return processResponse.success(Boolean.TRUE);
    }

}
