package ec.diners.com.domain.entities.rol;

import lombok.Data;

import java.util.Date;

@Data
public class FunctionalityDto {

    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
