package CryptOptima.server.global.exception;

import lombok.Getter;
import org.springframework.security.core.parameters.P;

public enum ExceptionCode {
    USER_NOT_FOUND(404,"User does not exists."),
    MANAGER_NOT_FOUND(404, "Manager does not exists."),
    EXCHANGE_NOT_FOUND(404,"Exchange does not exists."),
    COIN_NOT_FOUND(404, "Coin does not exists."),
    WITHDRAWAL_NOT_FOUND(404,"Withdrawal does not exists."),
    EXCEEDED_WITHDRAWAL_LIMIT(400,"The withdrawal request exceeds the available amount."),
    ACCESS_TOKEN_NOT_VALID(401, "Access token is not valid"),
    REFRESH_TOKEN_NOT_VALID(401, "Refresh token is not valid"),
    UNAUTHORIZED_USER(403, "Unauthorized user"),
    INVALID_APPROACH(403, "Invalid approach"),
    AUTHENTICATION_FAILED(404, "Id/Password does not match"),
    EXCEEDED_BROWSER_REGISTER(400, "Browser registration exceeds available registration."),
    REFRESH_TOKEN_NOT_EXISTS(403, "Refresh token does not exists"),
    ACCESS_TOKEN_EXISTS(403,"Access token exists"),
    ACCESS_TOKEN_EXPIRED(403, "Access token expired");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
