package ch.heigvd.broccoli.security;

import ch.heigvd.broccoli.domain.application.ApplicationRepository;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final ApplicationRepository repository;

    public ApiKeyAuthFilter(ApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        try {
            String apiKey = httpServletRequest.getHeader("X-API-KEY");
            if(apiKey != null) {
                return repository.findByApiKey(UUID.fromString(apiKey));
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }

}
