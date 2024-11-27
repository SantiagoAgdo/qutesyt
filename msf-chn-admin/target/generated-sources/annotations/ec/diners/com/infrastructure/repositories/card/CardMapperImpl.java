package ec.diners.com.infrastructure.repositories.card;

import ec.diners.com.domain.entities.card.Card;
import ec.diners.com.domain.entities.cardType.CardType;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardModel;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardTypeModel;
import ec.diners.com.infrastructure.repositories.cardType.CardTypeMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CardMapperImpl implements CardMapper {

    @Autowired
    private CardTypeMapper cardTypeMapper;

    @Override
    public Card toCard(CardModel model) {
        if ( model == null ) {
            return null;
        }

        Card card = new Card();

        card.setId( model.getId() );
        card.setName( model.getName() );
        card.setCreatorUserId( model.getCreatorUserId() );
        card.setUrlCard( model.getUrlCard() );
        card.setExternalCode( model.getExternalCode() );
        card.setCardType( cardTypeMapper.toCardsTypes( model.getCardTypeModel() ) );

        return card;
    }

    @Override
    public List<Card> toCards(List<CardModel> cardModels) {
        if ( cardModels == null ) {
            return null;
        }

        List<Card> list = new ArrayList<Card>( cardModels.size() );
        for ( CardModel cardModel : cardModels ) {
            list.add( toCard( cardModel ) );
        }

        return list;
    }

    @Override
    public CardModel toCardModel(Card card) {
        if ( card == null ) {
            return null;
        }

        CardModel cardModel = new CardModel();

        cardModel.setId( card.getId() );
        cardModel.setName( card.getName() );
        cardModel.setCreatorUserId( card.getCreatorUserId() );
        cardModel.setUrlCard( card.getUrlCard() );
        cardModel.setExternalCode( card.getExternalCode() );
        cardModel.setCardTypeModel( cardTypeListToCardTypeModelList( card.getCardType() ) );

        return cardModel;
    }

    protected List<CardTypeModel> cardTypeListToCardTypeModelList(List<CardType> list) {
        if ( list == null ) {
            return null;
        }

        List<CardTypeModel> list1 = new ArrayList<CardTypeModel>( list.size() );
        for ( CardType cardType : list ) {
            list1.add( cardTypeMapper.toCardTypeModel( cardType ) );
        }

        return list1;
    }
}
