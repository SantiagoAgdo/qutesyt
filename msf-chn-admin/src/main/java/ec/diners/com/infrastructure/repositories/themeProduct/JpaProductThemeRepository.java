package ec.diners.com.infrastructure.repositories.themeProduct;

import ec.diners.com.domain.entities.themeProduct.ProductThemeListAllView;
import ec.diners.com.infrastructure.modelsDb.theme.ProductThemeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaProductThemeRepository extends JpaRepository<ProductThemeModel, Long> {

    Optional<ProductThemeModel> findByCodeProduct(String codeProduct);

    List<ProductThemeModel> findByCodeProductIn(List<String> codeProducts);

    @Query("select pt.id as productThemeId, pt.codeProduct as codeProduct, cp.nameProductCard as productName, " +
            "cp.descriptionProductCard as description, t.id as themeId, " +
            "t.name as themeName from ProductThemeModel pt \n" +
            "join CardProductModel cp on pt.codeProduct = cp.codeProductCard \n" +
            "join ThemeModel t on t.id = pt.themeId order by pt.priority")
    List<ProductThemeListAllView> findAllProductsTheme();
    @Query("select count(pt.themeId) from ProductThemeModel pt where pt.themeId=:themeId")
    Long countTheme(Integer themeId);

}
