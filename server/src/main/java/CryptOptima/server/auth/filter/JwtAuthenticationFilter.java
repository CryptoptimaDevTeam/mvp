package CryptOptima.server.auth.filter;

import CryptOptima.server.domain.manager.Manager;
import CryptOptima.server.auth.dto.LoginDto;
import CryptOptima.server.auth.jwt.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenizer jwtTokenizer;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenizer jwtTokenizer) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenizer = jwtTokenizer;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        ObjectMapper objectMapper = new ObjectMapper(); // username과 password를 역직렬화 한다.
        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        // 1-1. 인증이 필요한 UsernamePasswordAuthenticationToken 객체를 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getAccountId(), loginDto.getPassword());

        // 1-2. AuthenticationManager에게 전달 & 인증완료된 Authentication 객체 리턴
        // ManagerDetails(UserDetails) = username + password + authorities 를 리턴함.
        Authentication authResult = authenticationManager.authenticate(authenticationToken); // (= authResult)
        return authResult;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain filterChain,
                                            Authentication authResult) throws IOException, ServletException {

        Manager manager = (Manager) authResult.getPrincipal(); // 인증 성공 시 Authentication의 Object principal 필드에 Manager객체가 할당된다.

        String accessToken = delegateAccessToken(manager);
        String refreshToken = delegateRefreshToken(manager);

        response.setHeader("Authorization","Bearer" + accessToken);
        response.setHeader("Refresh", refreshToken);

        // TODO Filter에서 JWT 발급 vs. SuccessHandler에서 JWT 발급
        // this.getSuccessHandler().onAuthenticationSuccess(request,response,authResult);
    }

    // accessToken 발급을 jwtTokenizer에게 위임.
    private String delegateAccessToken(Manager manager) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("managerId",manager.getManagerId()); // managerId
        claims.put("accountId", manager.getAccountId()); // managerAccountId
        claims.put("grade", manager.getManagerGrade()); // grade

        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims,expiration,base64EncodedSecretKey);

        return accessToken;
    }

    // refreshToken 발급을 jwtTokenizer에게 위임.
    private String delegateRefreshToken(Manager manager) {
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(expiration,base64EncodedSecretKey);

        return refreshToken;
    }
}
