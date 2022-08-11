package micro.spring.auth.util;

import io.jsonwebtoken.*;
import micro.spring.auth.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtTokenUtil {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    private final Logger LOGGER = Logger.getLogger(JwtTokenUtil.class.getName());

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(UserEntity user)
    {
        return Jwts.builder()
                .setSubject(String.format("%s, %s", user.getId(), user.getEmail()))
                .setIssuer("auth-service")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateAccessToken(String accessToken)
    {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken);
            return true;
        }catch (ExpiredJwtException ex) {
            LOGGER.log(Level.WARNING,"JWT expired" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.WARNING, "Token is null, empty or only whitespace" + ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.log(Level.WARNING, "JWT is invalid" + ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.log(Level.WARNING,"JWT is not supported" + ex);
        } catch (SignatureException ex) {
            LOGGER.log(Level.WARNING, "Signature validation failed");
        }

        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
