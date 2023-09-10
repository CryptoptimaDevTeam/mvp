package CryptOptima.server.global.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Default {
        private int status;
        private String message;
    }

    // 비즈니스로직 예외: 지정된 Code로 생성할 경우
    public static ErrorResponse.Default of(ExceptionCode exceptionCode) {
        return new ErrorResponse.Default(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    // 런타임 예외: 서버 시스템 상 에러
    public static ErrorResponse.Default of(Exception e) {
        return new ErrorResponse.Default(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Validation {
        private int status;
        private String message;
        private List<FieldError> fieldErrors;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String error;
    }

    public static ErrorResponse.Validation of(MethodArgumentNotValidException e) {
        return Validation.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("The value you entered is not valid.")
                .fieldErrors(
                        e.getFieldErrors().stream().map(
                                fieldError -> FieldError.builder()
                                        .field(fieldError.getField())
                                        .error(fieldError.getDefaultMessage())
                                        .build()
                        ).collect(Collectors.toList())
                ).build();
    }

    public static ErrorResponse.Validation of(ConstraintViolationException e) {
        return Validation.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("The value you entered is not valid.")
                .fieldErrors(
                        e.getConstraintViolations().stream().map(
                                constraintViolation -> FieldError.builder()
                                        .field(constraintViolation.getPropertyPath().toString())
                                        .error(constraintViolation.getMessage())
                                        .build()
                        ).collect(Collectors.toList())
                ).build();
    }
}
