package ec.diners.com.domain.entities.cardType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardType {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String userCreate;

}
