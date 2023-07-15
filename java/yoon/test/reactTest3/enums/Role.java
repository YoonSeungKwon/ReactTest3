package yoon.test.reactTest3.enums;

import lombok.Getter;

@Getter
public enum Role {

    GUEST(0, "ROLE_ANONYMOUS"),
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final int key;
    private final String value;

    Role(int key, String value){
        this.key = key;
        this.value = value;
    }

}
