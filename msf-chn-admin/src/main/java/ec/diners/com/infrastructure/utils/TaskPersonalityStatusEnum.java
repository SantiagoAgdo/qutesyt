package ec.diners.com.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPersonalityStatusEnum {
    IN_PROGRESS("In Progress"),
    PENDING("Pending"),
    COMPLETED("Completed"),
    CANCELED("Canceled");
    private final String value;
}
