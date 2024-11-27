package ec.diners.com.application.utils;

import ec.diners.com.domain.constants.LoginFieldConstants;
import ec.diners.com.domain.entities.user.User;
import ec.diners.com.domain.interfaces.security.JwtToken;
import ec.diners.com.infrastructure.security.JwtTokenImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class UserAuthenticationUtils {

    private static JwtToken jwtToken = new JwtTokenImpl();
    private static LoginFieldConstants loginFieldConstants = new LoginFieldConstants();

    private UserAuthenticationUtils(JwtToken jwtToken) {
        UserAuthenticationUtils.jwtToken = jwtToken;
        loginFieldConstants = new LoginFieldConstants();
    }

    public static String generateToken(User user, Date accessExpiresIn) {
        var claimMap = generateTokenClaims(user);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        claimMap.put(loginFieldConstants.getAccessTokenExpiresInField(), dateFormat.format(accessExpiresIn));
        return jwtToken.generateToken(claimMap);
    }

    public static String generateRefreshToken(User user, Date refreshExpiresIn) {
        var claimMap = generateTokenClaims(user);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        claimMap.put(loginFieldConstants.getRefreshTokenExpiresInField(), dateFormat.format(refreshExpiresIn));
        claimMap.put(loginFieldConstants.getIdField(), user.getId());
        claimMap.put(loginFieldConstants.getEmailField(), user.getEmail());
        claimMap.put(loginFieldConstants.getAliasField(), Objects.isNull(user.getAlias()) ? "" : user.getAlias());
        claimMap.put(loginFieldConstants.getFullNameField(), user.getName());
        claimMap.put(loginFieldConstants.getLoginTypeField(), "client");
        claimMap.put(loginFieldConstants.getMustBeCompletedField(), Objects.isNull(user.getAlias()));

        return jwtToken.generateRefreshToken(claimMap);
    }

    public static HashMap<String, Object> generateTokenClaims(User user) {
        var claimMap = new HashMap<String, Object>();
        claimMap.put(loginFieldConstants.getIdField(), user.getId());
        claimMap.put(loginFieldConstants.getEmailField(), user.getEmail());
        claimMap.put(loginFieldConstants.getAliasField(), Objects.isNull(user.getAlias()) ? "" : user.getAlias());
        claimMap.put(loginFieldConstants.getFullNameField(), user.getName());
        claimMap.put(loginFieldConstants.getLoginTypeField(), "client");
        claimMap.put(loginFieldConstants.getMustBeCompletedField(), Objects.isNull(user.getAlias()));

        return claimMap;
    }
}
