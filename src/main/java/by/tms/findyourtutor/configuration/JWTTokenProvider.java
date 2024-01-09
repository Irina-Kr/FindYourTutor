package by.tms.findyourtutor.configuration;

import by.tms.findyourtutor.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.jsonwebtoken.Jwts.*;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {
    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @PostConstruct
    protected void init(){
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String generateToken(String username, String password, Set<Role> roles){
     Claims claims = claims().setSubject(username);
     claims.put("password",password);
     claims.put("roles",getUserRoleFromJWT(roles));


     return  builder()
             .setClaims(claims)
             .compact();

    }

    public Authentication getAuthentication() {
        return getAuthentication(null);
    }

    public Authentication getAuthentication(String token){
        UserPrincipal userPrincipal = new UserPrincipal();

        userPrincipal.setUsername(getUsernameFromJWT(token));
        userPrincipal.setPassword(getUserPasswordFromJWT(token));
        userPrincipal.setRoles(getUserRoleFromJWT(token));

        return new UsernamePasswordAuthenticationToken(userPrincipal,"",userPrincipal.getAuthorities());

    }

    public Set<javax.management.relation.Role> getUserRoleFromJWT(String token) {
        List<String> roles = (List<String>)
                parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
        return getUserRoleFromJWT(token);
    }
    public String getUsernameFromJWT(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUserPasswordFromJWT(String token) {
        return (String) parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("password");
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    private Set<String> getUserRoleFromJWT(Set<Role> roles) {
        Set<String> result = new HashSet<>();
        roles.forEach(role -> result.add(role.getAuthority()));
        return result;
    }

    private Set<Role> getUserRoleFromJWT(List<String> roles) {
        Set<Role> result = new HashSet<>();
        roles.forEach(Role::valueOf);
        return result;
    }

}
