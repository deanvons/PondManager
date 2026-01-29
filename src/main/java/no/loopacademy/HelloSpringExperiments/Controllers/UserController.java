package no.loopacademy.HelloSpringExperiments.Controllers;

import no.loopacademy.HelloSpringExperiments.Models.User;
import no.loopacademy.HelloSpringExperiments.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    @PreAuthorize("hasAnyRole('admin','manager','SUPERUSER')")
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



    // actual usable endpoints

    // CREATE
    // the frontend should call this when it gets a new user via keycloak
    // will have to check if they exist and then of not call this
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user, @AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getSubject();

        // Never trust client identity fields
        user.setJwtSub(sub);

        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("appropriateParanoiaAddUser")
    public ResponseEntity<User> createWithTinHat(@RequestBody User user, @AuthenticationPrincipal Jwt jwt) {
        String sub = jwt.getSubject();

        if (user.getJwtSub() != null && !user.getJwtSub().equals(sub)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        user.setJwtSub(sub);
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ BY JWT SUB (handy for Keycloak integration)
    @GetMapping("/by-sub/{jwtSub}")
    public ResponseEntity<User> getByJwtSub(@PathVariable String jwtSub) {
        return userService.getByJwtSub(jwtSub)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
