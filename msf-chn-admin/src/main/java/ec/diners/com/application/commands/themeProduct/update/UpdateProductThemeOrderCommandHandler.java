package ec.diners.com.application.commands.themeProduct.update;

import an.awesome.pipelinr.Command;
import ec.diners.com.domain.interfaces.repositories.personalityChange.IPersonalityChangeRepository;
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
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class UpdateProductThemeOrderCommandHandler implements Command.Handler<UpdateProductThemeOrderCommand, Response<Boolean>> {

    private final IProductThemeRepository productThemeRepository;
    private final IPersonalityChangeRepository personalityChangeRepository;

    public UpdateProductThemeOrderCommandHandler(IProductThemeRepository productThemeRepository, IPersonalityChangeRepository personalityChangeRepository) {
        this.productThemeRepository = productThemeRepository;
        this.personalityChangeRepository = personalityChangeRepository;
    }

    @Override
    public Response<Boolean> handle( UpdateProductThemeOrderCommand updateProductThemeOrderCommand) {
        var processResponse = new ProcessResponse<Boolean>();
        PersonalizationChangeModel personalizationChangeModel = null;
        List<String> lstCodesProducts = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(1);
        updateProductThemeOrderCommand.getIdProductList().forEach(productThemeId-> {
            var objProductTheme= productThemeRepository.findById(productThemeId);
            if (objProductTheme!=null) {
                lstCodesProducts.add(objProductTheme.getCodeProduct());
                objProductTheme.setPriority(count.get());
                productThemeRepository.update(objProductTheme);
                count.getAndIncrement();
            }
        });

        if(CollectionUtils.isNotEmpty(lstCodesProducts)){
            //create personality_changes
            personalizationChangeModel = new PersonalizationChangeModel();
            personalizationChangeModel.setState(TaskPersonalityStatusEnum.PENDING.getValue());
            personalizationChangeModel.setName(TaskPersonalityNameEnum.UPDATE_ORDER_PRODUCT_THEME.getValue());
            personalizationChangeModel.setCode(String.join(",", lstCodesProducts.stream().map(Object::toString).toArray(String[]::new)));
            personalityChangeRepository.save(personalizationChangeModel);
        }
        return processResponse.success(Boolean.TRUE);
    }
}
