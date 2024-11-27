package ec.diners.com.infrastructure.repositories.themeProduct;

import ec.diners.com.domain.entities.themeProduct.ProductTheme;
import ec.diners.com.domain.entities.themeProduct.ProductThemeListAllView;
import ec.diners.com.domain.interfaces.repositories.themeProduct.IProductThemeRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductThemeRepository implements IProductThemeRepository {

    private final JpaProductThemeRepository jpaProductThemeRepository;

    private final ProductThemeMapper mapper;


    public ProductThemeRepository(JpaProductThemeRepository jpaProductThemeRepository, ProductThemeMapper mapper) {
        this.jpaProductThemeRepository = jpaProductThemeRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductTheme findByCodeProduct(String codeProduct) {
        var byCodeProduct = jpaProductThemeRepository.findByCodeProduct(codeProduct);
        if(!byCodeProduct.isPresent()){
            return null;
        }
        return mapper.toProductTheme(byCodeProduct.get());
    }

    @Override
    public List<ProductTheme> findByCodeProducts(List<String> codeProducts){
        var lstTerCon = jpaProductThemeRepository.findByCodeProductIn(codeProducts);
        if(CollectionUtils.isNotEmpty(lstTerCon)){
            return mapper.toEntities(lstTerCon);
        }
        return null;
    }

    @Override
    public Long save(ProductTheme productTheme) {
        return this.jpaProductThemeRepository.save(this.mapper.toProductThemeModel(productTheme)).getId();
    }

    @Override
    public void deleteById(Long id) {
        this.jpaProductThemeRepository.deleteById(id);
    }

    @Override
    public ProductTheme findById(Long id) {
        return this.jpaProductThemeRepository.findById(id).map(this.mapper::toProductTheme).orElse(null);
    }

    @Override
    public Boolean findTheme(int themeId) {
        return jpaProductThemeRepository.countTheme(themeId)>0;
    }

    @Override
    public List<ProductThemeListAllView> findAll() {
        return this.jpaProductThemeRepository.findAllProductsTheme();
    }

    @Override
    public Long update(ProductTheme productTheme) {
        return this.jpaProductThemeRepository.save(this.mapper.toProductThemeModel(productTheme)).getId();
    }
}
