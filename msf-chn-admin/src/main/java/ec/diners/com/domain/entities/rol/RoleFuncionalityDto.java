package ec.diners.com.domain.entities.rol;

import ec.diners.com.infrastructure.modelsDb.rol.RoleFunctionalityId;
import lombok.Data;

import java.util.Date;

@Data
public class RoleFuncionalityDto {

    private RoleFunctionalityId id;
    private Date assignedAt;
    private RoleDto role;
    private FunctionalityDto functionality;
}
