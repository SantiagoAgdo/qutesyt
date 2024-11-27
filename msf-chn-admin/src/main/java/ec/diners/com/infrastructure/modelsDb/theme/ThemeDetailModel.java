package ec.diners.com.infrastructure.modelsDb.theme;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "theme_details")
@Getter
@Setter
public class ThemeDetailModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "theme_id")
    private Long themeId;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "theme_id", insertable = false, updatable = false)
    private ThemeModel theme;

    @PrePersist
    public void prePersist(){
        this.uuid = UUID.randomUUID().toString();
    }

}
