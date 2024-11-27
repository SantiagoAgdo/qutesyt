package ec.diners.com.application.commands.themeProduct.register;

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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class CreateProductThemeCommandHandler implements Command.Handler<CreateProductThemeCommand, Response<Boolean>> {

    private final IProductThemeRepository productThemeRepository;
    private final IThemeRepository themeRepository;
    private final IPersonalityChangeRepository personalityChangeRepository;

    public CreateProductThemeCommandHandler(IProductThemeRepository productThemeRepository, IThemeRepository themeRepository, IPersonalityChangeRepository personalityChangeRepository) {
        this.productThemeRepository = productThemeRepository;
        this.themeRepository = themeRepository;
        this.personalityChangeRepository = personalityChangeRepository;
    }

    @Override
    public Response<Boolean> handle(CreateProductThemeCommand createProductThemeCommand) {
        var processResponse = new ProcessResponse<Boolean>();
        Theme theme = null;
        List<String> cardProductList = null;
        PersonalizationChangeModel personalizationChangeModel = null;

        theme = themeRepository.findById(createProductThemeCommand.getThemeId());

        if (theme == null) {
            return processResponse.error("Theme con el id no existe en la bd." + createProductThemeCommand.getThemeId());
        }

        cardProductList = createProductThemeCommand.getCodeProductList();

        List<String> lstCodeProducts = new ArrayList<>();
        cardProductList.forEach(codeProduct -> {
            if (!codeProduct.isEmpty()) {
                ProductTheme productThemeByCodeProductDatabase = productThemeRepository.findByCodeProduct(codeProduct);
                if (Objects.isNull(productThemeByCodeProductDatabase)) {
                    ProductTheme productTheme = new ProductTheme();
                    productTheme.setCodeProduct(codeProduct);
                    productTheme.setThemeId(createProductThemeCommand.getThemeId());
                    productTheme.setPriority(999);
                    productTheme.setCreatorUserId(createProductThemeCommand.getUser());
                    productThemeRepository.save(productTheme);
                    lstCodeProducts.add(codeProduct);
                }
            }
        });
        if(CollectionUtils.isNotEmpty(lstCodeProducts)){
            //save personality_changes
            personalizationChangeModel = new PersonalizationChangeModel();
            personalizationChangeModel.setState(TaskPersonalityStatusEnum.PENDING.getValue());
            personalizationChangeModel.setName(TaskPersonalityNameEnum.CREATE_PRODUCT_THEME.getValue());
            personalizationChangeModel.setCode(String.join(",", lstCodeProducts.stream().map(Object::toString).toArray(String[]::new)));
            personalityChangeRepository.save(personalizationChangeModel);
        }
        return processResponse.success(Boolean.TRUE);
    }

}
