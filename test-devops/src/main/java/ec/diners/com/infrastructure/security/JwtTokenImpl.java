package ec.diners.com.infrastructure.security;

import ec.diners.com.domain.constants.LoginFieldConstants;
import ec.diners.com.domain.interfaces.security.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenImpl implements JwtToken {

    private static final String jwtSecret = "a9bdks2dhks678dgfgJlk4glLKFJGLF6KKLGghdgDDFH6DDHFLKLG";
    private final LoginFieldConstants loginFieldConstants;

    public JwtTokenImpl() {
        this.loginFieldConstants = new LoginFieldConstants();
    }

    private Date createDateTimeValue(String valueStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueStr);
        } catch (ParseException ignored) {
            return null;
        }
    }

    public String generateToken(Map<String, Object> claimMap) {
        Date tokenExpiredIn = createDateTimeValue(claimMap.get(loginFieldConstants.getAccessTokenExpiresInField()).toString());
        return Jwts.builder()
                .addClaims(claimMap)
                .setSubject(claimMap.get(loginFieldConstants.getAliasField()).toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(tokenExpiredIn)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public String generateRefreshToken(Map<String, Object> claimMap) {
        Date tokenExpiredIn = createDateTimeValue(claimMap.get(loginFieldConstants.getRefreshTokenExpiresInField()).toString());
        return Jwts.builder()
                .addClaims(claimMap)
                .setSubject(claimMap.get(loginFieldConstants.getAliasField()).toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(tokenExpiredIn)
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public String generateResetPasswordToken(Map<String, Object> claimMap) {
        return Jwts.builder()
                .addClaims(claimMap)
                .setSubject(claimMap.get(loginFieldConstants.getAliasField()).toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public Boolean isValidToken(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        var isTokenExpired = claims.getExpiration().before(new Date());
        return !isTokenExpired;
    }

    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getUserTypeFromToken(String token) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get(loginFieldConstants.getLoginTypeField()).toString();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build().parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
}
