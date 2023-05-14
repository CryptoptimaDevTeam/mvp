package CryptOptima.server.auth.oauth.handler;

import CryptOptima.server.auth.jwt.JwtTokenizer;
import CryptOptima.server.domain.user.User;
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
        String accountId = oAuth2User.getEmail();

//        saveUser(accountId);
        redirect(request, response, accountId);
    }

    private void saveUser(String accountId) {
        User user = User.builder()
                .accountId(accountId)
                .status("ACTIVE")
                .paybackCumAmount("0")
                .paybackFinishedAmount("0")
                .paybackTotalRequestedAmount("0")
                .build();

        userService.createUser(user);
    }

    // TODO authorities 추가 가능성
    private void redirect(HttpServletRequest request, HttpServletResponse response, String accountId) throws IOException {
        String accessToken = delegateAccessToken(accountId);
        String refreshToken = delegateRefreshToken(accountId);

        String uri = createURI(accessToken, refreshToken).toString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

    // TODO authorities 추가 가능성
    private String delegateAccessToken(String accountId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", accountId);
        claims.put("grade","USER");

        String subject = accountId;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(String accountId) {
        String subject = accountId;
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
        return refreshToken;
    }

    private URI createURI(String accessToken, String refreshToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
//                .port(80)
                .path("/oauth")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
