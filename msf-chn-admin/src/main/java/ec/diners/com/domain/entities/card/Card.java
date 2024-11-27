package ec.diners.com.domain.entities.card;

import ec.diners.com.domain.entities.cardType.CardType;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Card {

    private Long id;
    private String name;
    private String creatorUserId;
    private String urlCard;
    private String externalCode;
    private List<CardType> cardType;

}
