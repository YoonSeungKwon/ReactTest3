package yoon.test.reactTest3.vo.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import yoon.test.reactTest3.domain.Members;

@Data
@AllArgsConstructor
public class PostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private String email;

}
