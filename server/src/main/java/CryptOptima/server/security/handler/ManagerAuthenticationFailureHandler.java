package CryptOptima.server.security.handler;

import CryptOptima.server.global.exception.ErrorResponse;
import CryptOptima.server.global.exception.ExceptionCode;
import CryptOptima.server.global.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class ManagerAuthenticationFailureHandler implements AuthenticationFailureHandler {

    ErrorResponder errorResponder = new ErrorResponder();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        errorResponder.sendErrorResponse(response, ErrorResponse.of(ExceptionCode.AUTHENTICATION_FAILED));
    }

}
