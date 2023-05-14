package CryptOptima.server.auth.handler;

import CryptOptima.server.global.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationException 발생 시 ErrorResponse를 Client에게 전송한다.
 */
@Slf4j
public class ManagerAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception"); // verifyJws 메서드 내 catch 구문에서 설정한 attribute
        ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);

        logExceptionMessage(authException, exception);
    }

    private void logExceptionMessage(AuthenticationException authException, Exception e) {
        String message = e!=null ? e.getMessage() : authException.getMessage();
        log.warn("Unauthorized error happend: {}", message);
    }
}
