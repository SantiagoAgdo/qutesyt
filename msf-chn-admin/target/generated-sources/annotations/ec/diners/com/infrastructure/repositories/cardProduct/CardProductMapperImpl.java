package ec.diners.com.infrastructure.repositories.cardProduct;

import ec.diners.com.domain.entities.card.Card;
import ec.diners.com.domain.entities.cardProduct.CardProduct;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardModel;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardProductModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-27T14:35:08-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class CardProductMapperImpl implements CardProductMapper {

    @Override
    public CardProduct toCardProduct(CardProductModel model) {
        if ( model == null ) {
            return null;
        }

        CardProduct cardProduct = new CardProduct();

        cardProduct.setId( model.getId() );
        cardProduct.setCodeProduct( model.getCodeProductCard() );
        cardProduct.setNameProduct( model.getNameProductCard() );
        cardProduct.setDescriptionProduct( model.getDescriptionProductCard() );
        cardProduct.setUserCreate( model.getCreatorUserId() );
        cardProduct.setUrlImage( model.getUrlImage() );
        cardProduct.setUrlAnimation( model.getUrlAnimation() );
        cardProduct.setPriority( model.getPriority() );
        cardProduct.setCard( cardModelToCard( model.getCard() ) );

        return cardProduct;
    }

    @Override
    public List<CardProduct> toCardProducts(List<CardProductModel> models) {
        if ( models == null ) {
            return null;
        }

        List<CardProduct> list = new ArrayList<CardProduct>( models.size() );
        for ( CardProductModel cardProductModel : models ) {
            list.add( toCardProduct( cardProductModel ) );
        }

        return list;
    }

    @Override
    public CardProductModel toCardProductModel(CardProduct card) {
        if ( card == null ) {
            return null;
        }

        CardProductModel cardProductModel = new CardProductModel();

        cardProductModel.setId( card.getId() );
        cardProductModel.setCodeProductCard( card.getCodeProduct() );
        cardProductModel.setNameProductCard( card.getNameProduct() );
        cardProductModel.setDescriptionProductCard( card.getDescriptionProduct() );
        cardProductModel.setCreatorUserId( card.getUserCreate() );
        cardProductModel.setUrlImage( card.getUrlImage() );
        cardProductModel.setUrlAnimation( card.getUrlAnimation() );
        cardProductModel.setPriority( card.getPriority() );
        cardProductModel.setCard( cardToCardModel( card.getCard() ) );

        return cardProductModel;
    }

    protected Card cardModelToCard(CardModel cardModel) {
        if ( cardModel == null ) {
            return null;
        }

        Card card = new Card();

        card.setCreatorUserId( cardModel.getCreatorUserId() );
        card.setExternalCode( cardModel.getExternalCode() );
        card.setId( cardModel.getId() );
        card.setName( cardModel.getName() );
        card.setUrlCard( cardModel.getUrlCard() );

        return card;
    }

    protected CardModel cardToCardModel(Card card) {
        if ( card == null ) {
            return null;
        }

        CardModel cardModel = new CardModel();

        cardModel.setCreatorUserId( card.getCreatorUserId() );
        cardModel.setExternalCode( card.getExternalCode() );
        cardModel.setId( card.getId() );
        cardModel.setName( card.getName() );
        cardModel.setUrlCard( card.getUrlCard() );

        return cardModel;
    }
}
