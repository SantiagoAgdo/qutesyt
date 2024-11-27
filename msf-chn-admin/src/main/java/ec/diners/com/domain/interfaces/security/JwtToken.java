package ec.diners.com.domain.interfaces.security;

import java.util.Date;
import java.util.Map;

public interface JwtToken {
    String generateToken(Map<String, Object> claimMap);
    String generateRefreshToken(Map<String, Object> claimMap);
    String generateResetPasswordToken(Map<String, Object> claimMap);
    String getUsernameFromToken(String token);
    String getUserTypeFromToken(String token);
    Boolean isValidToken(String token);
    Date getExpirationDateFromToken(String token);
}
