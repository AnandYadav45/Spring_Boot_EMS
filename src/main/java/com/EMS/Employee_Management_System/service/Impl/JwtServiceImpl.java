package com.EMS.Employee_Management_System.service.Impl;


import com.EMS.Employee_Management_System.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private Long jwtExpiration;


    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims :: getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return GenerateToken(new HashMap<>(), userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {

        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);

    }

    private String GenerateToken(HashMap<String, Object> extraClaims, UserDetails userDetails){

        return buildToken(extraClaims, userDetails, jwtExpiration);

    }

    private String buildToken(HashMap<String, Object> extraClaims, UserDetails userDetails, Long expiration){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();

    }

    private Key getSecretKey(){

        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }


}
