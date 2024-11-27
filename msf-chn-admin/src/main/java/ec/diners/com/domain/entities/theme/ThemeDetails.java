package ec.diners.com.domain.entities.theme;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThemeDetails {
    Long id;
    String uuid;
    Long themeId;
    String name;
    String value;
    String createUser;
}
