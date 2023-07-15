package yoon.test.reactTest3.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDto {

    private String email;

    private String password;

    private String name;

}
