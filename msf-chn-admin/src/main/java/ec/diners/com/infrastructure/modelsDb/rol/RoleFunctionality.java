package ec.diners.com.infrastructure.modelsDb.rol;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "RoleFunctionality")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunctionality {
    @EmbeddedId
    private RoleFunctionalityId id;

    @Column(name = "assignedAt")
    @CreationTimestamp
    private Date assignedAt;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private RoleModel role;

    @ManyToOne
    @MapsId("functionalityId")
    @JoinColumn(name = "functionality_id", nullable = false)
    private Functionality functionality;
}
