package ec.diners.com.domain.entities.theme;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Theme {

    private Long id;

    private String uuid;

    private String name;

    private String description;

    private String userCreate;

    private String userUpdate;

    private Date updatedAt;

    private Date createAt;

    private Boolean isActive;

}
