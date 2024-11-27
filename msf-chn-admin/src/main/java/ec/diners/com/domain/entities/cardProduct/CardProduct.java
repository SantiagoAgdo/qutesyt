package ec.diners.com.domain.entities.cardProduct;

import ec.diners.com.domain.entities.card.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardProduct {

    private Long id;
    private String codeProduct;
    private String nameProduct;
    private String descriptionProduct;
    private String userCreate;
    private String urlImage;
    private String urlAnimation;
    private int priority;
    private Card card;

}
