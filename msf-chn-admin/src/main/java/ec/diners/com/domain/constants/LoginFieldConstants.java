package ec.diners.com.domain.constants;

public class LoginFieldConstants {
    private final String idField;
    private final String emailField;
    private final String aliasField;
    private final String fullNameField;
    private final String loginTypeField;
    private final String mustBeCompletedField;
    private final String accessTokenExpiresInField;
    private final String refreshTokenExpiresInField;

    public LoginFieldConstants() {
        this.mustBeCompletedField = "mustBeCompleted";
        this.loginTypeField = "loginType";
        this.idField = "id";
        this.emailField = "email";
        this.aliasField = "alias";
        this.fullNameField = "fullName";
        this.accessTokenExpiresInField = "accessTokenExpiresIn";
        this.refreshTokenExpiresInField = "refreshTokenExpiresInField";
    }

    public String getAliasField() {
        return aliasField;
    }


    public String getFullNameField() {
        return fullNameField;
    }

    public String getAccessTokenExpiresInField() {
        return accessTokenExpiresInField;
    }

    public String getRefreshTokenExpiresInField() {
        return refreshTokenExpiresInField;
    }

    public String getIdField() {
        return idField;
    }

    public String getEmailField() {
        return emailField;
    }

    public String getLoginTypeField() {
        return loginTypeField;
    }

    public String getMustBeCompletedField() {
        return mustBeCompletedField;
    }
}
