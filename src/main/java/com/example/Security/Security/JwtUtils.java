package com.example.Security.Security;

import com.example.Security.Service.UserDetailsService;
import io.jsonwebtoken.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;


@Component
public class JwtUtils {


    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;
    private UserDetailsService userDetails;


    public String generateJwtToken(Authentication authentication) {
        UserDetailsService userPrincipal = (UserDetailsService) authentication.getPrincipal();
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
    //validate token

    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return false;
    }


    //retrieve username from jwt token
    public String getUsernameFromToken(String auToken) {
        return getClaimFromToken(auToken, Claims::getSubject);
    }


    public <T> T getClaimFromToken(String auToken, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(auToken);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String auToken) {
        return null;
    }

    public String getEmailFromJwtToken(String jwt) {

        return jwt;
    }
}


