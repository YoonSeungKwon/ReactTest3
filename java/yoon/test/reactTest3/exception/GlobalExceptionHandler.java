package yoon.test.reactTest3.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yoon.test.reactTest3.enums.ErrorCode;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleConflictException(MethodArgumentNotValidException e){
        String error = e.getAllErrors().get(0).getDefaultMessage();
        System.out.println(error);
        if(Objects.requireNonNull(error).equals("EMAIL_CONFLICTED"))
            return ResponseEntity.badRequest().body(ErrorCode.EMAIL_CONFLICTED.getMessage());
        else if(error.equals("EMAIL_BLANK"))
            return ResponseEntity.badRequest().body(ErrorCode.EMAIL_BLANK.getMessage());
        else if(error.equals("EMAIL_FORMAT"))
            return ResponseEntity.badRequest().body(ErrorCode.EMAIL_FORMAT.getMessage());
        else if(error.equals("PASSWORD_BLANK"))
            return ResponseEntity.badRequest().body(ErrorCode.PASSWORD_BLANK.getMessage());
        else if(error.equals("NAME_BLANK"))
            return ResponseEntity.badRequest().body(ErrorCode.NAME_BLANK.getMessage());
        else
            return null;
    }

}
