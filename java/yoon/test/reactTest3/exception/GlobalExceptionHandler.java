package yoon.test.reactTest3.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import yoon.test.reactTest3.enums.ErrorCode;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleValidException(MethodArgumentNotValidException e){
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
            return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<?> handleUsernameException(){
        return ResponseEntity.badRequest().body(ErrorCode.EMAIL_NOT_FOUND.getMessage());
    }
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> handlePasswordException(){
        return ResponseEntity.badRequest().body(ErrorCode.PASSWORD_NOT_FOUND.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class})
    protected ResponseEntity<?> handleAuthException(){
        System.out.println("error");
        return ResponseEntity.badRequest().body(ErrorCode.USER_AUTHORIZATION_ERROR.getMessage());
    }
}
