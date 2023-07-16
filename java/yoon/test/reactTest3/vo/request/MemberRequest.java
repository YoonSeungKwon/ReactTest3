package yoon.test.reactTest3.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

}
