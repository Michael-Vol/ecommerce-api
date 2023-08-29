package com.michaelvol.ecommerceapi.security;

import com.michaelvol.ecommerceapi.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static com.michaelvol.ecommerceapi.utils.AppConstants.JWT_TOKEN_VALIDITY;

@Service
public class JwtService {
    @Value("${ecommerce.api.jwtSecret}")
    private String secretKey;

    public String getUsername(String token) {
        return extractClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token) {
        return extractClaimFromToken(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public boolean isTokenValid(String token, String username) {
        return getUsername(token).equals(username) && !isTokenExpired(token);
    }

    public String generateToken(Map<String, Object> claims, User principal) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(principal.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * JWT_TOKEN_VALIDITY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Generic method to generate token from authentication object
    public String generateToken(Map<String, Object> claims, Authentication authentication) {
        return generateToken(claims, (User) authentication.getPrincipal());
    }

    public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
