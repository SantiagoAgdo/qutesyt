package ec.diners.com.application.dtos.request.theme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateThemeDetailRequest {
    private Long id;
    private Long themeId;
    private String name;
    private String value;
}
