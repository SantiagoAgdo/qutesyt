package ec.diners.com.presentation.controllers.security;

public record CsrfTokenDto(String headerName, String parameterName, String token) {
}
