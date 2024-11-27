package ec.diners.com.domain.entities.rol;

import lombok.Data;

@Data
public class RoleDto {
    private String roleId;
    private String name;
    private String description;
    private boolean enabled;
}// dto funcionality