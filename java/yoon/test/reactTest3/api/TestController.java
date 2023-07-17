package yoon.test.reactTest3.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoon.test.reactTest3.domain.Members;
import yoon.test.reactTest3.service.MemberService;
import yoon.test.reactTest3.vo.response.MemberResponse;

import java.security.Principal;

@RestController
@RequestMapping("/test/v1")
@RequiredArgsConstructor
public class TestController {

    private final MemberService memberService;

    @GetMapping("/user")
    public ResponseEntity<?> userData(){
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MemberResponse memberResponse = memberService.getResponse(member);

        return ResponseEntity.ok().body(memberResponse);
    }

}
