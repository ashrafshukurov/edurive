package az.edurive.course.security;

import az.edurive.course.model.enums.TokenType;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${security.jwtSecret}")
    private String jwtSecret;
    @Value("${security.accessTokenExpirationInMs}")
    private String accessTokenExpirationInMs;
    @Value("${security.refreshTokenExpirationInMs}")
    private String refreshTokenExpirationInMs;

    public String generateToken(UserDetails userDetails, TokenType tokenType){
        var expirationInMs =tokenType==TokenType.ACCESS_TOKEN?accessTokenExpirationInMs:refreshTokenExpirationInMs;
        Date now=new Date();
        Date expiryDate=new Date(now.getTime()+Integer.parseInt(expirationInMs));
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public String getGmailFromJwt(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            log.error("Invalid JWT signature");
        }catch (MalformedJwtException e){
            log.error("Invalid Jwt token");
        }catch (ExpiredJwtException e){
            log.error("Expired JWT token");
        }catch (UnsupportedJwtException e){
            log.error("Unsupported JWT token");
        }catch (IllegalArgumentException e){
            log.error("JWt claims string is empty");
        }
          return false;
    }
}
