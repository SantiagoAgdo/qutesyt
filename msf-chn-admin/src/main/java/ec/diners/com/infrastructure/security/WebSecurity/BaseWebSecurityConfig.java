package ec.diners.com.infrastructure.security.WebSecurity;

import jakarta.servlet.ServletException;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class BaseWebSecurityConfig extends GlobalMethodSecurityConfiguration {

    SecurityFilterChain filterChain(HttpSecurity http) throws ServletException {
        http.getClass();
        return null;
    }
}
