package yoon.test.reactTest3.vo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yoon.test.reactTest3.enums.StatusEnums;

@Getter
@Setter
public class Message {

    private StatusEnums status;

    private String message;

    private Object data;

    public Message(){
        this.status = StatusEnums.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }

}
