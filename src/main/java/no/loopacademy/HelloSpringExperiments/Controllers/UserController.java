package no.loopacademy.HelloSpringExperiments.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 1) Minimal "who am I" endpoint: read claims directly from JWT
    @GetMapping("/me")
    public Map<String, Object> me(@org.springframework.security.core.annotation.AuthenticationPrincipal Jwt jwt) {
        // Keycloak stable user id (use this as FK/externalId in your DB)
        String keycloakUserId = jwt.getSubject(); // == "sub"

        // Common KC/OpenID claims (may vary by realm/client scopes)
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");
        String givenName = jwt.getClaimAsString("given_name");
        String familyName = jwt.getClaimAsString("family_name");

        // You can also return raw claims for teaching/debugging
        return Map.of(
                "kcUserId", keycloakUserId,
                "username", username,
                "email", email,
                "givenName", givenName,
                "familyName", familyName,
                "issuer", jwt.getIssuer(),
                "audience", jwt.getAudience(),
                "expiresAt", jwt.getExpiresAt(),
                "allClaims", jwt.getClaims()
        );
    }

    // 2) Show roles/authorities Spring sees (after our KeycloakRolesConverter)
    @GetMapping("/me/roles")
    public Map<String, Object> myRoles(Authentication authentication) {
        var authorities = authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .sorted()
                .toList();

        return Map.of(
                "principalType", authentication.getClass().getSimpleName(),
                "name", authentication.getName(),
                "authorities", authorities
        );
    }

    // 3) Role-protected endpoint
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin/secret")
    public Map<String, String> adminOnly() {
        return Map.of("message", "You are an admin (ROLE_admin).");
    }

    // 4) Example: require either role
    @PreAuthorize("hasAnyRole('admin','manager')")
    @GetMapping("/management")
    public Map<String, String> management() {
        return Map.of("message", "You are admin or manager.");
    }

    // 5) If you want to access the token object with authorities + jwt in one place:
    @GetMapping("/me/token-debug")
    public Map<String, Object> tokenDebug(JwtAuthenticationToken token) {
        Jwt jwt = token.getToken();
        return Map.of(
                "sub", jwt.getSubject(),
                "preferred_username", jwt.getClaimAsString("preferred_username"),
                "authorities", token.getAuthorities().stream().map(a -> a.getAuthority()).toList()
        );
    }
}
