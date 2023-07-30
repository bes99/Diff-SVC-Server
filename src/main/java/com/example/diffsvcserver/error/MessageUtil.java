package com.example.diffsvcserver.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageUtil {
    SUCCESS("Success"),
    FAIL("Fail"),
    USER_NOT_FOUND("일치하는 회원 정보가 없습니다. 사용자 id를 확인해주세요."),
    DUPLICATE_USER_ID("이미 사용중인 아이디입니다.");
    private final String message;

}
