package yoon.test.reactTest3.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import yoon.test.reactTest3.exception.valid.ValidationGroups;

@Data
public class MemberRequest {

    @NotBlank(message = "EMAIL_BLANK", groups = ValidationGroups.EmailNotNull.class)
    @Email(message = "EMAIL_FORMAT", groups = ValidationGroups.EmailNotFormat.class)
    private String email;

    @NotBlank(message = "PASSWORD_BLANK", groups = ValidationGroups.PasswordNotNull.class)
    private String password;

}
