package ec.diners.com.infrastructure.utils.ThemesPriority;

import ec.diners.com.domain.entities.themeProduct.ProductTheme;
import ec.diners.com.domain.interfaces.repositories.themeProduct.IProductThemeRepository;
import ec.diners.com.domain.utils.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class ProductThemeUtil {

    private final IProductThemeRepository productThemeRepository;

    // Valor configurable para evitar dependencia directa de Constants
    private final Long defaultThemeId;

    public ProductThemeUtil(IProductThemeRepository productThemeRepository) {
        this.productThemeRepository = productThemeRepository;
        this.defaultThemeId = Constants.PARAM_DEFAUL_THEME_APP; // Cambiar a una configuración externa si es necesario
    }

    /**
     * Obtiene el ID del tema del producto basado en los productos principales.
     *
     * @param mainProducts Cadena de productos principales separada por "|"
     * @return ID del tema del producto o el tema por defecto si no se encuentra
     */
    public Long getProductThemeId(String mainProducts) {
        // Validación temprana para evitar procesos innecesarios
        if (StringUtils.isBlank(mainProducts)) {
            return defaultThemeId;
        }

        // Dividir productos principales
        List<String> productList = Arrays.asList(mainProducts.split("\\|"));

        // Buscar temas de productos en el repositorio
        List<ProductTheme> productThemes = productThemeRepository.findByCodeProducts(productList);

        // Retornar el ID del tema con mayor prioridad o el tema por defecto
        return CollectionUtils.emptyIfNull(productThemes)
                .stream()
                .min(Comparator.comparing(ProductTheme::getPriority)) // Obtener el de mayor prioridad
                .map(ProductTheme::getThemeId) // Extraer el ID del tema
                .orElse(defaultThemeId); // Retornar por defecto si no hay resultados
    }
}

