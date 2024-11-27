package ec.diners.com.application.dtos.response.roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRoleResponse {

    private Long id;
    private String name;
    private String description;

    public GetRoleResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
