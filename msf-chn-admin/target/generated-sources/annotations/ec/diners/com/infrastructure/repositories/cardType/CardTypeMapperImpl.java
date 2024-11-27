package ec.diners.com.infrastructure.repositories.cardType;

import ec.diners.com.domain.entities.cardType.CardType;
import ec.diners.com.infrastructure.modelsDb.cardProduct.CardTypeModel;
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
public class CardTypeMapperImpl implements CardTypeMapper {

    @Override
    public CardType toCardType(CardTypeModel model) {
        if ( model == null ) {
            return null;
        }

        CardType cardType = new CardType();

        cardType.setId( model.getId() );
        cardType.setCode( model.getCodeTypeCard() );
        cardType.setName( model.getNameTypeCard() );
        cardType.setDescription( model.getDescriptionTypeCard() );
        cardType.setUserCreate( model.getCreatorUserId() );

        return cardType;
    }

    @Override
    public List<CardType> toCardsTypes(List<CardTypeModel> models) {
        if ( models == null ) {
            return null;
        }

        List<CardType> list = new ArrayList<CardType>( models.size() );
        for ( CardTypeModel cardTypeModel : models ) {
            list.add( toCardType( cardTypeModel ) );
        }

        return list;
    }

    @Override
    public CardTypeModel toCardTypeModel(CardType cardType) {
        if ( cardType == null ) {
            return null;
        }

        CardTypeModel cardTypeModel = new CardTypeModel();

        cardTypeModel.setId( cardType.getId() );
        cardTypeModel.setCodeTypeCard( cardType.getCode() );
        cardTypeModel.setNameTypeCard( cardType.getName() );
        cardTypeModel.setDescriptionTypeCard( cardType.getDescription() );
        cardTypeModel.setCreatorUserId( cardType.getUserCreate() );

        return cardTypeModel;
    }
}
