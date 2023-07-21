package CryptOptima.server.security.handler;

import CryptOptima.server.security.jwt.JwtTokenizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ManagerAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String accessToken = JwtTokenizer.generateAccessToken(authentication.getPrincipal());
        String refreshToken = JwtTokenizer.generateRefreshToken();

        // TODO redis에 RT저장 (accountId : RT)
        response.setHeader("Authorization", "Bearer" + accessToken);
        response.addHeader("Refresh", refreshToken);
    }
}
