package ec.diners.com.infrastructure.repositories.cardProduct;

import ec.diners.com.domain.entities.cardProduct.CardProduct;
import ec.diners.com.domain.entities.cardProduct.CardView;
import ec.diners.com.domain.interfaces.repositories.cardProduct.ICardProductRepository;
import ec.diners.com.infrastructure.repositories.card.CardMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CardProductRepository implements ICardProductRepository {

    private final CardProductMapper mapperProduct;
    private final CardMapper mapper;
    private final JpaCardProductRepository jpaCardProductRepository;

    public CardProductRepository(CardProductMapper mapperProduct, CardMapper mapper, JpaCardProductRepository jpaCardProductRepository) {
        this.mapperProduct = mapperProduct;
        this.mapper = mapper;
        this.jpaCardProductRepository = jpaCardProductRepository;
    }

    @Override
    public List<CardProduct> findByProduct(String codeProductCard) {
        var cardProductModelList = jpaCardProductRepository.findByCodeProductCard(codeProductCard);
        if (!cardProductModelList.isPresent())
            return Collections.emptyList();

        return mapperProduct.toCardProducts(cardProductModelList.get());
    }

    @Override
    public CardProduct findById(long id) {
        var cardProductMode = jpaCardProductRepository.findById(id);
        if (!cardProductMode.isPresent())
            return null;

        return mapperProduct.toCardProduct(cardProductMode.get());
    }

    @Override
    public List<String> findCardsByProduct(List<String> prods) {
        var products = jpaCardProductRepository.findCardByProduct(prods);
        if (!products.isPresent())
            return Collections.emptyList();
        return products.get();
    }

    @Override
    public CardProduct findCardsByProductByOrderAsc(List<String> prods) {
        CardProduct cardProduct = null;
        var lstCardProduct = jpaCardProductRepository.findCardByProductByOrderAsc(prods);
        if(lstCardProduct != null){
           cardProduct = this.mapperProduct.toCardProduct(CollectionUtils.get(lstCardProduct.get(), 0));
        }
        return cardProduct;
    }

    @Override
    public List<CardView> findExternalLinkCard(List<String> prods) {
        return jpaCardProductRepository.findExternalLinkCard(prods, true);
    }

    @Override
    public List<CardProduct> findByName(String name) {
        return this.jpaCardProductRepository.findByNameProductCardContaining(name).stream().map(this.mapperProduct::toCardProduct).collect(Collectors.toList());
    }

    @Override
    public List<CardProduct> findAll() {
        return this.jpaCardProductRepository.findAll().stream().map(this.mapperProduct::toCardProduct).collect(Collectors.toList());
    }

    @Override
    public List<CardProduct> findAllRecordsNotInProductTheme() {
        return this.jpaCardProductRepository.findAllRecordsNotInProductTheme().stream().map(this.mapperProduct::toCardProduct).collect(Collectors.toList());
    }
}
