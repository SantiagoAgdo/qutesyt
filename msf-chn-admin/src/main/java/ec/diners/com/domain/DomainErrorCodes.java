package ec.diners.com.domain;

public class DomainErrorCodes {

    public static final String loginErrorCode = "LoginError";
    public static final String loginErrorMessageKey = "user.badCredentials";

    public static final String accessDeniedErrorCode = "AccessDeniedError";
    public static final String accessDeniedErrorMessageKey = "user.accessDenied";

    public static final String notSignedErrorCode = "NotSignedError";
    public static final String notSignedErrorMessageKey = "user.notSigned";

    public static final String lockedUserErrorCode = "LoginError";
    public static final String lockedUserErrorMessageKey = "user.locked";

    public static final String unauthorizedErrorCode = "UnauthorizedError";
    public static final String unauthorizedErrorMessageKey = "user.unauthorized";

    public static final String tokenExpiredErrorCode = "UnauthorizedError";
    public static final String tokenExpiredErrorMessageKey = "user.tokenExpired";

    public static final String userNotFoundErrorCode = "UserNotFoundError";
    public static final String userNotFoundErrorMessageKey = "user.notFound";

    public static final String termsOrPolicyNotFoundErrorCode = "TermsOrPolicyError";
    public static final String termsOrPolicyNotFoundErrorMessageKey = "termsOrPolicy.notFound";

    public static final String wrongPasswordErrorCode = "PasswordError";
    public static final String wrongPasswordErrorMessageKey = "user.wrongPawd";



    private DomainErrorCodes() {

    }


}
