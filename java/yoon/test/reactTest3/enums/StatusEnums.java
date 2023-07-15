package yoon.test.reactTest3.enums;

import lombok.Getter;

@Getter
public enum StatusEnums {

    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    StatusEnums(int code){
        this.code = code;
    }

}
