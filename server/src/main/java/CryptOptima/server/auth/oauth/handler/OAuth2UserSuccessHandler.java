package CryptOptima.server.auth.oauth.handler;

import CryptOptima.server.auth.jwt.JwtTokenizer;
import CryptOptima.server.domain.user.UserService;
import CryptOptima.server.auth.oauth.dto.OAuth2CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OAuth2UserSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final UserService userService;

    public OAuth2UserSuccessHandler(JwtTokenizer jwtTokenizer, UserService userService) {
        this.jwtTokenizer = jwtTokenizer;
        this.userService = userService;
    }

    // 인증 성공 시 수행되는 메서드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2CustomUser oAuth2User = (OAuth2CustomUser) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Long userId = oAuth2User.getUserId();

        redirect(request, response, userId, attributes);
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, Long userId, Map<String, Object> attributes) throws IOException {
        // attribute 에서 email과 username을 꺼낸다.
        String email = (String) attributes.get("email");
        String username = (String) attributes.get("name");

        String accessToken = delegateAccessToken(email, userId);
        String refreshToken = delegateRefreshToken(email);

        String uri = createURI(accessToken, refreshToken, userId, username, email).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    private String delegateAccessToken(String accountId, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); // userId
        claims.put("accountId", accountId); // email(accountId)
        claims.put("grade","USER"); // grade

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String accountId) {
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(expiration, base64EncodedSecretKey);
        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken, Long userId, String email, String userName) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();

        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);
        queryParams.add("user_id", String.valueOf(userId));
        queryParams.add("username", String.valueOf(userName));
        queryParams.add("email", String.valueOf(email));

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(3000)
                .path("/oauth")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
