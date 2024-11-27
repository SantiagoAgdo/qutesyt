package ec.diners.com.infrastructure.security.WebSecurity;

import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.ServletException;

public class BaseWebSecurityConfig extends GlobalMethodSecurityConfiguration {

    SecurityFilterChain filterChain(HttpSecurity http) throws ServletException {
        http.getClass();
        return null;
    }
}
