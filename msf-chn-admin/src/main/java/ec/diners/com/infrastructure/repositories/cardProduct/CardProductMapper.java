package ec.diners.com.infrastructure.repositories.cardProduct;

import ec.diners.com.domain.entities.cardProduct.CardProduct;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardProductModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardProductMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="codeProductCard", target="codeProduct")
    @Mapping(source="nameProductCard", target="nameProduct")
    @Mapping(source="descriptionProductCard", target="descriptionProduct")
    @Mapping(source="creatorUserId", target="userCreate")
    @Mapping(source="urlImage", target="urlImage")
    @Mapping(source="urlAnimation", target="urlAnimation")
    @Mapping(source="priority", target="priority")
   // @Mapping(source="card", target="card")
    CardProduct toCardProduct(CardProductModel model);

    List<CardProduct> toCardProducts(List<CardProductModel> models);

    @InheritInverseConfiguration
    CardProductModel toCardProductModel(CardProduct card);

}
