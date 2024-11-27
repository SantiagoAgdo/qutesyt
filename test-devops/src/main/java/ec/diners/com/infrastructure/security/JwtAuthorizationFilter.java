package ec.diners.com.infrastructure.security;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.interfaces.security.JwtToken;
import ec.diners.com.domain.response.ProcessResponse;
import ec.diners.com.domain.response.Response;
import ec.diners.com.domain.services.security.user.getByAccessToken.GetUserByAccessTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final Pipeline mediator;
    private final JwtToken token;

    public JwtAuthorizationFilter(Pipeline mediator, JwtToken token) {
        this.mediator = mediator;
        this.token = token;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String tokenValue = null;

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            tokenValue = tokenHeader.substring(7);
        }

        if (tokenValue != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var loginUserResponse = getLoginUserByToken(tokenValue);
            if (loginUserResponse.isSuccess() && token.isValidToken(tokenValue)){
                var loginUser = loginUserResponse.getValue();
                var authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private Response<LoginUser> getLoginUserByToken(String tokenValue) {
        var processResponse = new ProcessResponse<LoginUser>();

        LoginUser loginUser = new LoginUser();
        var userType = token.getUserTypeFromToken(tokenValue);

        if (userType.equals("client")){
            var userResponse = mediator.send(new GetUserByAccessTokenService(tokenValue));
            if (!userResponse.isSuccess()) {
                return processResponse.error(userResponse);
            }

            var user = userResponse.getValue();
            loginUser.setId(user.getId());
            loginUser.setEmail(user.getEmail());
            loginUser.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("Client")));
            return processResponse.success(loginUser);
        }

            return processResponse.error("User Type not valid");

    }
}
