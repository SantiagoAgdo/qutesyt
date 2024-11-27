package ec.diners.com.infrastructure.modelsDb.theme;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "product_theme")
@Getter
@Setter
public class ProductThemeModel extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_product")
    private String codeProduct;

    @Column(name = "theme_id")
    private Long themeId;

    @Column(name = "priority")
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_id", insertable = false, updatable = false)
    private ThemeModel theme;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date(System.currentTimeMillis());
        this.isActive = Boolean.TRUE;
    }

    @PreUpdate
    public void PreUpdate(){
        this.updatedAt = new Date(System.currentTimeMillis());
    }

}
