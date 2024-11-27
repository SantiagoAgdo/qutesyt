package ec.diners.com.domain.entities.personality;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonalizationValidationDto {
    List<String> lstPartners;
    List<String> nameGroupOperation;
}
