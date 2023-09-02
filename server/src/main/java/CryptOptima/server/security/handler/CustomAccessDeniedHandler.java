package CryptOptima.server.security.handler;

import CryptOptima.server.global.exception.ErrorResponse;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.utils.ErrorResponder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ErrorResponder.sendErrorResponse(response, ErrorResponse.of(ExceptionCode.UNAUTHORIZED_USER));
    } // ExceptionTranslationFilter 에서 AccessDeniedException 발생 시 호출된다.
}
