package yoon.test.reactTest3.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.reactTest3.enums.StatusEnums;
import yoon.test.reactTest3.service.MemberService;
import yoon.test.reactTest3.vo.request.MemberDto;
import yoon.test.reactTest3.vo.request.MemberRequest;
import yoon.test.reactTest3.vo.response.MemberResponse;
import yoon.test.reactTest3.vo.response.Message;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    //Member Register
    @PostMapping("/join")
    public ResponseEntity<Message> memberJoin(MemberDto dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
        Message message = new Message();
        try {
            MemberResponse result = memberService.join(dto);
            message.setStatus(StatusEnums.OK);
            message.setMessage("Register Success");
            message.setData(result);
        }catch(Exception e){
            message.setStatus(StatusEnums.BAD_REQUEST);
            message.setMessage("Register Failed");
            message.setData(e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

    //Member Login
    @PostMapping("/login")
    public ResponseEntity<Message> memberLogin(MemberRequest dto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "JSON", Charset.forName("UTF-8")));
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
