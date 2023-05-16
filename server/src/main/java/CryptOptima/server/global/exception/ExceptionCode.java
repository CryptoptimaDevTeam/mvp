package CryptOptima.server.global.exception;

import lombok.Getter;
import org.springframework.security.core.parameters.P;

public enum ExceptionCode {
    MANAGER_NOT_FOUND(404, "Manager does not exists."),
    USER_NOT_FOUND(404,"User does not exists."),
    EXCHANGE_NOT_FOUND(404,"Exchange does not exists."),
    ACCESS_TOKEN_NOT_VALID(400, "Access token is not valid"),
    REFRESH_TOKEN_NOT_VALID(400, "Refresh token is not valid"),
    AUTHENTICATION_FAILED(404,"Id/Password does not match");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
