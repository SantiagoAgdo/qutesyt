package ec.diners.com.domain.entities.themeProduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTheme {

    private Long id;
    private String codeProduct;
    private Long themeId;
    private Integer priority;
    private String creatorUserId;
    private Boolean isActive;
    private String updaterUserId;
    private Date createdAt;

}
