package ec.diners.com.infrastructure.repositories.themeProduct;

import ec.diners.com.domain.entities.themeProduct.ProductTheme;
import ec.diners.com.infrastructure.modelsDb.theme.ProductThemeModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ProductThemeMapperImpl implements ProductThemeMapper {

    @Override
    public ProductTheme toProductTheme(ProductThemeModel model) {
        if ( model == null ) {
            return null;
        }

        ProductTheme productTheme = new ProductTheme();

        productTheme.setId( model.getId() );
        productTheme.setCodeProduct( model.getCodeProduct() );
        productTheme.setThemeId( model.getThemeId() );
        productTheme.setPriority( model.getPriority() );
        productTheme.setCreatorUserId( model.getCreatorUserId() );
        productTheme.setIsActive( model.getIsActive() );
        productTheme.setUpdaterUserId( model.getUpdaterUserId() );
        productTheme.setCreatedAt( model.getCreatedAt() );

        return productTheme;
    }

    @Override
    public List<ProductTheme> toEntities(List<ProductThemeModel> models) {
        if ( models == null ) {
            return null;
        }

        List<ProductTheme> list = new ArrayList<ProductTheme>( models.size() );
        for ( ProductThemeModel productThemeModel : models ) {
            list.add( toProductTheme( productThemeModel ) );
        }

        return list;
    }

    @Override
    public ProductThemeModel toProductThemeModel(ProductTheme productTheme) {
        if ( productTheme == null ) {
            return null;
        }

        ProductThemeModel productThemeModel = new ProductThemeModel();

        productThemeModel.setId( productTheme.getId() );
        productThemeModel.setCodeProduct( productTheme.getCodeProduct() );
        productThemeModel.setThemeId( productTheme.getThemeId() );
        productThemeModel.setPriority( productTheme.getPriority() );
        productThemeModel.setCreatorUserId( productTheme.getCreatorUserId() );
        productThemeModel.setIsActive( productTheme.getIsActive() );
        productThemeModel.setUpdaterUserId( productTheme.getUpdaterUserId() );
        productThemeModel.setCreatedAt( productTheme.getCreatedAt() );

        return productThemeModel;
    }
}
