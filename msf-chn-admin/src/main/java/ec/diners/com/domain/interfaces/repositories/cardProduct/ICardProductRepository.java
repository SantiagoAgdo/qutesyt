package ec.diners.com.domain.interfaces.repositories.cardProduct;

import ec.diners.com.domain.entities.cardProduct.CardProduct;
import ec.diners.com.domain.entities.cardProduct.CardView;

import java.util.List;

public interface ICardProductRepository {

    List<CardProduct> findByProduct(String codProduct);
    CardProduct findById(long id);
    List<String> findCardsByProduct(List<String> prods);
    CardProduct findCardsByProductByOrderAsc(List<String> prods);
    List<CardView> findExternalLinkCard(List<String> prods);
    List<CardProduct> findByName(String name);
    List<CardProduct> findAll();
    List<CardProduct> findAllRecordsNotInProductTheme();

}
