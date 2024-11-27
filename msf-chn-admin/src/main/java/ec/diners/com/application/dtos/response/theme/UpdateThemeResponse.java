package ec.diners.com.application.dtos.response.theme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateThemeResponse {
    private Long id;

    public UpdateThemeResponse(Long id) {
        this.id = id;
    }
}
