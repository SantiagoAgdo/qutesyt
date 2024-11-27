package ec.diners.com.domain.response;

public record OperationResponse<T>(
        boolean isSuccess,
        T value,
        String errorResponse
) {
    public static <T> OperationResponse<T> success(T value) {
        return new OperationResponse<>(true, value, null);
    }

    public static <T> OperationResponse<T> error(String error) {
        return new OperationResponse<>(false, null, error);
    }
}
