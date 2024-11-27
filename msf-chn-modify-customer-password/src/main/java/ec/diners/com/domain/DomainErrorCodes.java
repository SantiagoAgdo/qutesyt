package ec.diners.com.domain;

public class DomainErrorCodes {

    public static final String LOGIN_ERROR_CODE = "LoginError";
    public static final String LOGIN_ERROR_MESSAGE_KEY = "user.badCredentials";

    public static final String ACCESS_DENIED_ERROR_CODE = "AccessDeniedError";
    public static final String ACCESS_DENIED_ERROR_MESSAGE_KEY = "user.accessDenied";

    public static final String NOT_SIGNED_ERROR_CODE = "NotSignedError";
    public static final String NOT_SIGNED_ERROR_MESSAGE_KEY = "user.notSigned";

    public static final String LOCKED_USER_ERROR_CODE = "LoginError";
    public static final String LOCKED_USER_ERROR_MESSAGE_KEY = "user.locked";

    public static final String UNAUTHORIZED_ERROR_CODE = "UnauthorizedError";
    public static final String UNAUTHORIZED_ERROR_MESSAGE_KEY = "user.unauthorized";

    public static final String TOKEN_EXPIRED_ERROR_CODE = "UnauthorizedError";
    public static final String TOKEN_EXPIRED_ERROR_MESSAGE_KEY = "user.tokenExpired";

    public static final String USER_NOT_FOUND_ERROR_CODE = "UserNotFoundError";
    public static final String USER_NOT_FOUND_ERROR_MESSAGE_KEY = "user.notFound";

    public static final String TERMS_OR_POLICY_NOT_FOUND_ERROR_CODE = "TermsOrPolicyError";
    public static final String TERMS_OR_POLICY_NOT_FOUND_ERROR_MESSAGE_KEY = "termsOrPolicy.notFound";

    public static final String WRONG_PASSWORD_ERROR_CODE = "PasswordError";
    public static final String WRONG_PASSWORD_ERROR_MESSAGE_KEY = "user.wrongPawd";

    private DomainErrorCodes() {

    }
}
