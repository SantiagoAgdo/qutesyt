package ec.diners.com.infrastructure.security.WebSecurity;

import ec.diners.com.infrastructure.security.JwtAuthenticationEntryPoint;
import ec.diners.com.infrastructure.security.JwtAuthenticationFilter;
import ec.diners.com.infrastructure.security.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import jakarta.servlet.ServletException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends BaseWebSecurityConfig {

    @Value("${csrf.cookie.domain}")
    private String csrfCookieDomain;

    @Value("${csrf.cookie.name}")
    private String csrfCookieName;

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private static final String[] AUTH_WHITELIST = {"/v3/api-docs/**",
            "/swagger-ui/**",
            "/users/login",
            "/users/register",
            "/oauth2/**",
            "/csrf",
            "/refresh-token",
    };

    public WebSecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    @Override
    SecurityFilterChain filterChain(HttpSecurity http) throws ServletException {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        try {
            http
                    .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
                    // Disable CSRF using a function reference
                    .csrf(AbstractHttpConfigurer::disable) // Desactiva CSRF
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(AUTH_WHITELIST).permitAll() // Lista blanca de URLs
                            .anyRequest().authenticated() // Cualquier otra solicitud debe estar autenticada
                    )
                    // TODO
                    .exceptionHandling(ex -> ex
                            .authenticationEntryPoint(authenticationEntryPoint) // Configura el EntryPoint
                    )
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Política de sesiones
                    )
                    // Añade tus filtros
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Configura tus orígenes permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Si se permite el envío de cookies o credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public CsrfTokenRepository cookieCsrfTokenRepository() {
        CookieCsrfTokenRepository repository = new CookieCsrfTokenRepository();
        repository.setCookieHttpOnly(true);
        repository.setCookieMaxAge(5); // Five seconds
        repository.setCookieDomain(csrfCookieDomain);
        repository.setCookieName(csrfCookieName);
        repository.setSecure(true);
        return repository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/home");
    }
}