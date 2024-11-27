package ec.diners.com.infrastructure.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskPersonalityNameEnum {

    EXECUTE_PERSONALITY_ALL("Execute personality all"),

    CREATE_SEGMENT_THEME("Create segment theme"),
    UPDATE_SEGMENT_THEME("Update segment theme"),

    CREATE_PRODUCT_THEME("Create product theme"),
    UPDATE_PRODUCT_THEME("Update product theme"),
    UPDATE_ORDER_PRODUCT_THEME("Update order product theme"),
    DELETE_PRODUCT_THEME("Delete product theme"),

    CREATE_SEGMENT_BANNER("Create segment banner"),
    CREATE_PRODUCT_BANNER("Create product banner"),

    CREATE_SEGMENT_MODULE("Create segment module"),
    CREATE_PRODUCT_MODULE("Create product module");
    private final String value;
}
