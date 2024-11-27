package ec.diners.com.domain.models;

public class FilterValueType {

    private FilterValueType() {
        throw new IllegalStateException("Utility class");
    }

    public static final String INTEGER = "Integer";
    public static final String DOUBLE = "Double";
    public static final String BOOLEAN = "Boolean";
    public static final String DATE = "Date";
    public static final String DATE_TIME = "DateTime";
    public static final String TIME = "Time";
    public static final String UUID = "UUID";
}
