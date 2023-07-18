package yoon.test.reactTest3.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponse {

    private long idx;

    private String title;

    private String writer;

    private LocalDateTime regdate;

    private int hit;
}
