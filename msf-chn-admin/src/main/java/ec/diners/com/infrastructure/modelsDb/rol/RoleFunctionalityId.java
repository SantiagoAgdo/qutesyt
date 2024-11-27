package ec.diners.com.infrastructure.modelsDb.rol;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleFunctionalityId implements java.io.Serializable {
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "functionality_id")
    private Long functionalityId;
}
