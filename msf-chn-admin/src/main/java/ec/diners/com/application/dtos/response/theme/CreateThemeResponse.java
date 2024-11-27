package ec.diners.com.application.dtos.response.theme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateThemeResponse {
    private Long id;
    public CreateThemeResponse(Long id) {
        this.id = id;
    }
}
