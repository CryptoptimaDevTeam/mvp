package CryptOptima.server.security.handler;

import CryptOptima.server.common.exception.ErrorResponse;
import CryptOptima.server.common.exception.ExceptionCode;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ManagerAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.error("# Authentication failed: {}", exception.getMessage());
        sendErrorResponse(response);
    }
    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        Gson gson = new Gson();

        ErrorResponse errorResponse = ErrorResponse.of(ExceptionCode.AUTHENTICATION_FAILED);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(ExceptionCode.AUTHENTICATION_FAILED.getStatus());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
