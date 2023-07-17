package yoon.test.reactTest3.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //BAD_REQUEST

    EMAIL_BLANK(400, "이메일을 입력해 주세요"),
    EMAIL_FORMAT(400, "이메일 형식이 올바르지 않습니다."),
    PASSWORD_BLANK(400, "비밀번호를 입력해 주세요"),
    NAME_BLANK(400, "이름을 입력해 주세요"),

    //USER NOT_FOUND

    EMAIL_NOT_FOUND(400, "이메일이 존재하지 않습니다."),
    PASSWORD_NOT_FOUND(400, "이메일 또는 비밀번호가 일치하지 않습니다."),

    //UNAUTHORIZED

    USER_AUTHORIZATION_ERROR(401, "로그인이 필요한 페이지입니다."),
    ADMIN_AUTHORIZATION_ERROR(401, "관리자 전용 페이지입니다."),

    //PAGE NOT_FOUND

    PAGE_NOT_FOUND(404, "페이지가 존재하지 않습니다."),

    //CONFLICT INFO

    EMAIL_CONFLICTED(409, "이미 가입된 이메일 주소입니다."),
    USER_CONFLICTED(409, "이미 가입된 회원입니다."),

    //INTERNAL_SERVER_ERROR

    INTERNAL_SERVER_ERROR(500, "서버에 에러가 발생하였습니다.");


    private final int status;
    private final String message;

}


