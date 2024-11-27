package ec.diners.com.infrastructure.repositories.themeProduct;

import ec.diners.com.domain.entities.themeProduct.ProductTheme;
import ec.diners.com.infrastructure.modelsDb.theme.ProductThemeModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductThemeMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="codeProduct", target="codeProduct")
    @Mapping(source="themeId", target="themeId")
    @Mapping(source="priority", target="priority")
    @Mapping(source="creatorUserId", target="creatorUserId")
    @Mapping(source="isActive", target="isActive")
    @Mapping(source="updaterUserId", target="updaterUserId")
    @Mapping(source="createdAt", target="createdAt")
    ProductTheme toProductTheme(ProductThemeModel model);

    List<ProductTheme> toEntities(List<ProductThemeModel> models);

    @InheritInverseConfiguration
    ProductThemeModel toProductThemeModel(ProductTheme productTheme);
}
