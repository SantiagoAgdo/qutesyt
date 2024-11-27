package ec.diners.com.infrastructure.repositories.card;

import ec.diners.com.domain.entities.card.Card;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardModel;
import ec.diners.com.infrastructure.repositories.cardType.CardTypeMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {CardTypeMapper.class})
public interface CardMapper {
    @Mapping(source="id", target="id")
    @Mapping(source="name", target="name")
    @Mapping(source="creatorUserId", target="creatorUserId")
    @Mapping(source="urlCard", target="urlCard")
    @Mapping(source="externalCode", target="externalCode")
    @Mapping(source="cardTypeModel", target="cardType")
    Card toCard(CardModel model);

    List<Card> toCards(List<CardModel> cardModels);

    @InheritInverseConfiguration
    CardModel toCardModel(Card card);
}
