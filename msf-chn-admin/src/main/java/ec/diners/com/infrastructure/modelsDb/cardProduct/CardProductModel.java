package ec.diners.com.infrastructure.modelsDb.cardProduct;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "card_product")
@Getter
@Setter
public class CardProductModel extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String codeProductCard;

    @Column(name = "name")
    private String nameProductCard;

    @Column(name = "description")
    private String descriptionProductCard;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "url_animation")
    private String urlAnimation;

    @Column(name = "priority")
    private int priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    private CardModel card;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date(System.currentTimeMillis());
        this.setIsActive(true);
    }

}
