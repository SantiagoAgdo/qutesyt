package ec.diners.com.infrastructure.modelsDb.cardProduct;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "card_type")
@Getter
@Setter
public class CardTypeModel extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String codeTypeCard;

    @Column(name = "name")
    private String nameTypeCard;

    @Column(name = "description")
    private String descriptionTypeCard;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    private CardModel card;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date(System.currentTimeMillis());
        this.setIsActive(true);
    }

}
