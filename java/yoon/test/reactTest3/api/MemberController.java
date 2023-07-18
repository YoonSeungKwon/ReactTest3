package yoon.test.reactTest3.api;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yoon.test.reactTest3.enums.ErrorCode;
import yoon.test.reactTest3.enums.StatusEnums;
import yoon.test.reactTest3.exception.valid.LoginValidationSequence;
import yoon.test.reactTest3.exception.valid.RegisterValidationSequence;
import yoon.test.reactTest3.security.jwt.JwtProvider;
import yoon.test.reactTest3.service.MemberService;
import yoon.test.reactTest3.service.RefreshTokenService;
import yoon.test.reactTest3.vo.request.MemberDto;
import yoon.test.reactTest3.vo.request.MemberRequest;
import yoon.test.reactTest3.vo.response.MemberResponse;
import yoon.test.reactTest3.vo.response.Message;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    private final RefreshTokenService tokenService;

    private final JwtProvider jwtProvider;

    @GetMapping("/{email}/exists")
    public ResponseEntity<String> emailDuplicationCheck(@PathVariable String email){
        System.out.println(email);
        if(!memberService.checkEmailDuplication(email))
            return ResponseEntity.ok("사용가능한 이메일 주소입니다.");
        return ResponseEntity.badRequest().body(ErrorCode.EMAIL_CONFLICTED.getMessage());
    }

    //Member Register
    @PostMapping("/join")
    public ResponseEntity<Message> memberJoin(@RequestBody @Validated(RegisterValidationSequence.class) MemberDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", StandardCharsets.UTF_8));
        Message message = new Message();

        MemberResponse result = memberService.join(dto);

        message.setStatus(StatusEnums.OK);
        message.setMessage("회원가입 성공. 로그인 페이지로 이동합니다.");
        message.setData(result);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //Member Login
    @PostMapping("/login")
    public ResponseEntity<?> memberLogin(@RequestBody @Validated(LoginValidationSequence.class) MemberRequest dto, HttpServletResponse response){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", StandardCharsets.UTF_8));
        Message message = new Message();

        MemberResponse result = memberService.login(dto);

        //Make Tokens
        String accToken = jwtProvider.createAccessToken(result);
        String refToken = tokenService.updateRefreshToken(result);

        //Saving Tokens
        response.setHeader("Authorization", accToken);
        response.setHeader("X-RefreshToken", refToken);

        message.setStatus(StatusEnums.OK);
        message.setMessage(result.getName() + "님 환영합니다.");
        message.setData(result);
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}
