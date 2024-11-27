package ec.diners.com.infrastructure.modelsDb.cardProduct;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "card")
@Getter
@Setter
public class CardModel extends AbstractBaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "order_card")
    private String orderCard;

    @Column(name = "style_card")
    private String styleCard;

    @Column(name = "url_card")
    private String urlCard;

    @Column(name = "external_code")
    private String externalCode;

    @Column(name = "external_link")
    private String externalLink;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    private List<CardTypeModel> cardTypeModel;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date(System.currentTimeMillis());
        this.uuid = UUID.randomUUID().toString();
        this.setIsActive(true);
    }
}
