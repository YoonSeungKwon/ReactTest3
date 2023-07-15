package yoon.test.reactTest3.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private String email;

    private String name;

    private String roleValue;

    private LocalDateTime regdate;

}
