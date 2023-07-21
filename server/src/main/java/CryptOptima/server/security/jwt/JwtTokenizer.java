package CryptOptima.server.security.jwt;

import CryptOptima.server.domain.manager.Manager;
import CryptOptima.server.domain.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenizer {

    private static String secretKey;
    private static int accessTokenExpirationMinutes;
    private static int refreshTokenExpirationMinutes;

    @Value("${jwt.key}")
    private void setSecretKey(String key) {
        JwtTokenizer.secretKey = key;
    }

    @Value("${jwt.access-token-expiration-minutes}")
    private void setAccessTokenExpirationMinutes(String accessTokenExpirationMinutes) {
        JwtTokenizer.accessTokenExpirationMinutes = Integer.parseInt(accessTokenExpirationMinutes);
    }

    @Value("${jwt.refresh-token-expiration-minutes}")
    private void setRefreshTokenExpirationMinutes(String refreshTokenExpirationMinutes) {
        JwtTokenizer.refreshTokenExpirationMinutes = Integer.parseInt(refreshTokenExpirationMinutes);
    }

    private static Key getSecretKey() {
        return Keys.hmacShaKeyFor(JwtTokenizer.secretKey.getBytes());
    }

    // 1. AccessToken을 발행한다.
    public static <T> String generateAccessToken(T user) { // Todo 사용자, 매니저를 통합할 수 있는 방법
        return Jwts.builder()
                .setClaims(createClaims(user))
                .setExpiration(getTokenExpiration(JwtTokenizer.accessTokenExpirationMinutes))
                .signWith(getSecretKey())
                .compact();
    }

    // 2. RefreshToken을 발행한다.
    public static String generateRefreshToken() {
        return Jwts.builder()
                .setExpiration(getTokenExpiration(JwtTokenizer.refreshTokenExpirationMinutes))
                .signWith(getSecretKey())
                .compact();
    }

    // 3. claims를 얻는다.
    public static Jws<Claims> parseClaims(String jws) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(jws);
    }

    // 4. 토큰 만료시각을 리턴한다.
    private static Date getTokenExpiration(int expirationMinutes) {
        Calendar calendar = Calendar.getInstance(); // 현재 시각을 리턴한다.
        calendar.add(Calendar.MINUTE, expirationMinutes);
        Date expiration = calendar.getTime();

        return expiration;
    }

    // 5. claims를 생성한다.
    private static <T> Map<String, Object> createClaims(T user) {
        Map<String, Object> claims = new HashMap<>();

        if (user instanceof User) {
            claims.put("userId", ((User) user).getUserId());
            claims.put("role", "USER");
        } else {
            claims.put("managerId", ((Manager) user).getManagerId());
            claims.put("role", "MANAGER");
        }

        return claims;
    }
}
