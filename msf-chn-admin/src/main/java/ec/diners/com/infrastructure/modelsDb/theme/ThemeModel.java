package ec.diners.com.infrastructure.modelsDb.theme;

import ec.diners.com.infrastructure.modelsDb.AbstractBaseAuditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "theme")
@Getter
@Setter
public class ThemeModel extends AbstractBaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @PrePersist
    public void prePersist(){
        this.uuid = UUID.randomUUID().toString();
        this.createdAt = new Date(System.currentTimeMillis());
        this.isActive = Boolean.TRUE;
    }

}
