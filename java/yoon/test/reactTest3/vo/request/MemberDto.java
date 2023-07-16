package yoon.test.reactTest3.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import yoon.test.reactTest3.exception.RegisterValidationGroups;

@Data
@AllArgsConstructor
public class MemberDto {

    @NotBlank(message = "EMAIL_BLANK", groups = RegisterValidationGroups.EmailNotNull.class)
    @Email(message = "EMAIL_FORMAT", groups = RegisterValidationGroups.EmailNotFormat.class)
    private String email;

    @NotBlank(message = "PASSWORD_BLANK", groups = RegisterValidationGroups.PasswordNotNull.class)
    private String password;

    @NotBlank(message = "NAME_BLANK", groups = RegisterValidationGroups.NameNotNull.class)
    private String name;

}
