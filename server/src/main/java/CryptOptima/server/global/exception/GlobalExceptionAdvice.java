package CryptOptima.server.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity handleException(BusinessLogicException e) {
        final ErrorResponse.Default response = ErrorResponse.of(e.getExceptionCode());
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        final ErrorResponse.Default response = ErrorResponse.of(e);
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException e) {
        ErrorResponse.Validation response = ErrorResponse.of(e);
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleException(ConstraintViolationException e) {
        ErrorResponse.Validation response = ErrorResponse.of(e);
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatus()));
    }

}
