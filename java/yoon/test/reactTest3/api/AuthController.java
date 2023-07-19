package yoon.test.reactTest3.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkAuth(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")){
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true);
    }

}
