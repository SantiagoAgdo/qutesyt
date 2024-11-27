package ec.diners.com.infrastructure.repositories.cardProduct;

import ec.diners.com.domain.entities.cardProduct.CardView;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaCardProductRepository extends JpaRepository<CardProductModel,Long> {

    Optional<List<CardProductModel>> findByCodeProductCard(String codeProductCard);

    @Query(value = "select distinct card.id from CardProductModel  p  where p.codeProductCard IN (:prods)")
    Optional<List<String>> findCardByProduct(@Param("prods")List<String> prods);

    @Query(value = "select p from CardProductModel p where p.codeProductCard IN (:prods) order by p.priority asc")
    Optional<List<CardProductModel>> findCardByProductByOrderAsc(@Param("prods")List<String> prods);

    @Query("SELECT p.codeProductCard AS codeProduct, c.name AS name, c.externalLink AS externalLink from CardProductModel p inner join p.card c where p.codeProductCard IN (:prods) and p.isActive = :isActive and c.isActive = :isActive")
    List<CardView> findExternalLinkCard(@Param("prods") List<String> prods, @Param("isActive") boolean isActive);

    List<CardProductModel> findByNameProductCardContaining(String name);

    @Query("select cp from CardProductModel cp \n" +
            "where cp.codeProductCard not in(select pt.codeProduct from ProductThemeModel pt)")
    List<CardProductModel> findAllRecordsNotInProductTheme();

}
