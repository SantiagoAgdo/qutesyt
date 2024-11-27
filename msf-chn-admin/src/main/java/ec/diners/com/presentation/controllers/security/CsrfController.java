package ec.diners.com.presentation.controllers.security;

import ec.diners.com.presentation.controllers.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csrf")
public class CsrfController extends BaseController {

    @GetMapping()
    public ResponseEntity<CsrfTokenDto> getCsrfToken(HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        var csrfTokenResponse = new CsrfTokenDto(
                sanitizeString(csrfToken.getHeaderName()),
                sanitizeString(csrfToken.getParameterName()),
                sanitizeString(csrfToken.getToken())
        );
        return ResponseEntity.ok(csrfTokenResponse);
    }
}
