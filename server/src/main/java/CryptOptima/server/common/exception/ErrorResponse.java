package CryptOptima.server.common.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private int status;
    private String message;

    private ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ExceptionCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    // TODO Controller DTO 유효성 검증 에러 핸들러 추가
}
