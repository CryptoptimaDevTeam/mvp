package CryptOptima.server.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    private int status;
    private String message;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 비즈니스로직 예외: 지정된 Code로 생성할 경우
    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    // 런타임 예외: 서버 시스템 상 에러
    public static ErrorResponse of(Exception e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
