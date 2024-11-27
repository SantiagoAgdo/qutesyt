package ec.diners.com.domain.interfaces.repositories.themeProduct;

import ec.diners.com.domain.entities.themeProduct.ProductTheme;
import ec.diners.com.domain.entities.themeProduct.ProductThemeListAllView;

import java.util.List;

public interface IProductThemeRepository {

    ProductTheme findByCodeProduct(String codeProduct);
    List<ProductTheme> findByCodeProducts(List<String> codeProduct);
    Long save(ProductTheme productTheme);
    void deleteById(Long id);
    ProductTheme findById(Long id);
    Boolean findTheme(int themeId);
    List<ProductThemeListAllView> findAll();
    Long update(ProductTheme productTheme);

}
