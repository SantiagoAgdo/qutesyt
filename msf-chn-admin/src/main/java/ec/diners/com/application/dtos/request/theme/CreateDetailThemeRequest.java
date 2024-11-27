package ec.diners.com.application.dtos.request.theme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDetailThemeRequest {
    private Long themeId;
    private String name;
    private String value;

}
