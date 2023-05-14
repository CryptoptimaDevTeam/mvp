package CryptOptima.server.security.handler;

import CryptOptima.server.common.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AccessDeniedException 발생 시 ErrorResponse를 출력 스트림으로 생성한다.
 */
@Slf4j
public class ManagerAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
    }
}
