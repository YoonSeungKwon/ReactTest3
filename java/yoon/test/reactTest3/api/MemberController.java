package yoon.test.reactTest3.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yoon.test.reactTest3.enums.ErrorCode;
import yoon.test.reactTest3.enums.StatusEnums;
import yoon.test.reactTest3.service.MemberService;
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

    @GetMapping("/{email}/exists")
    public ResponseEntity<String> emailDuplicationCheck(@PathVariable String email){
        if(!memberService.checkEmailDuplication(email))
            return ResponseEntity.ok(email);
        return ResponseEntity.badRequest().body(ErrorCode.EMAIL_CONFLICTED.getMessage());
    }

    //Member Register
    @PostMapping("/join")
    public ResponseEntity<Message> memberJoin(@RequestBody @Validated MemberDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", StandardCharsets.UTF_8));
        Message message = new Message();

        MemberResponse result = memberService.join(dto);

        message.setStatus(StatusEnums.OK);
        message.setMessage("Register Success");
        message.setData(result);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //Member Login
    @PostMapping("/login")
    public ResponseEntity<?> memberLogin(@RequestBody @Validated MemberRequest dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", StandardCharsets.UTF_8));
        Message message = new Message();
        try {
            MemberResponse result = memberService.login(dto);
            message.setStatus(StatusEnums.OK);
            message.setMessage("Login Success");
            message.setData(result);
        }catch(Exception e){
            message.setStatus(StatusEnums.BAD_REQUEST);
            message.setMessage("Login Failed");
            message.setData(e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

}
