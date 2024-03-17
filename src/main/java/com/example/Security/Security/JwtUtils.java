package com.example.Security.Security;

import com.example.Security.Service.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtUtils {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;



    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Date dateJwtDate = new Date();
        dateJwtDate.getTime();

        return Jwts.builder().setSubject((userPrincipal.getEmail())).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }
    public String doGenerateToken(String email) {
        Claims claims = (Claims) Jwts.builder().setSubject(email);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));


        return Jwts.builder().setClaims(claims).setIssuer("http://devglan.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }
    public String getEmailFromJwtToken(String token) {


    }

    public boolean validateJwtToken(String authToken) {
        try {

            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.log(Level.parse("Invalid JWT signature: {}"), e.getMessage());
        } catch (MalformedJwtException e) {
            logger.log(Level.parse("Invalid JWT token: {}"), e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.log(Level.parse("JWT token is expired: {}"), e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.parse("JWT token is usnupported: {}"), e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.parse("JWT claims string is empty: {}"), e.getMessage());
        }

        return false;
    }


}
