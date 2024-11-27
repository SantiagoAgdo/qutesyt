package ec.diners.com.infrastructure.modelsDb.security;

import ec.diners.com.infrastructure.modelsDb.BaseDbModel;
import ec.diners.com.infrastructure.modelsDb.user.base.UserDbModel;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users_auth_tokens")
public class UserAuthTokenDbModel extends BaseDbModel {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    private UserDbModel user;
    @Column(name = "user_id", insertable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID userId;
    @Column(columnDefinition = "TEXT")
    private String accessToken;
    @Column(columnDefinition = "TEXT")
    private String refreshToken;
    private Date accessTokenExpiresIn;
    private Date refreshTokenExpiresIn;

    public UserAuthTokenDbModel() {
    }

    public UserDbModel getUser() {
        return user;
    }

    public void setUser(UserDbModel user) {
        this.user = user;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(Date accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public Date getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(Date refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }
}
