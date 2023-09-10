package CryptOptima.server.security.handler;

import CryptOptima.server.domain.manager.entity.Manager;
import CryptOptima.server.security.jwt.JwtService;
import CryptOptima.server.security.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ManagerAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String accessToken = JwtTokenizer.generateAccessToken(authentication.getPrincipal());
        String refreshToken = JwtTokenizer.generateRefreshToken(authentication.getPrincipal());

        // redis에 RT 저장
        jwtService.login(((Manager) authentication.getPrincipal()).getAccountId(), refreshToken);

        response.setHeader("Authorization", "Bearer" + accessToken);
        response.addHeader("Refresh", refreshToken);
    }
}
