package ec.diners.com.infrastructure.repositories.cardType;

import ec.diners.com.domain.entities.cardType.CardType;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardTypeModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    @Mapping(source="id", target="id")
    @Mapping(source="codeTypeCard", target="code")
    @Mapping(source="nameTypeCard", target="name")
    @Mapping(source="descriptionTypeCard", target="description")
    @Mapping(source="creatorUserId", target="userCreate")
    CardType toCardType(CardTypeModel model);

    List<CardType> toCardsTypes(List<CardTypeModel> models);

    @InheritInverseConfiguration
    CardTypeModel toCardTypeModel(CardType cardType);

}
