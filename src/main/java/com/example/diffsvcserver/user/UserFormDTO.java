package com.example.diffsvcserver.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserFormDTO {
    @NotBlank(message = "아이디 입력은 필수입니다.")
    private String userId;

    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    @Length(min = 8, max = 16, message = "8자 이상, 16자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}", message = "올바르지 않은 형식의 비밀번호입니다.")
    private String userPwd;

    @NotEmpty(message = "성별 입력은 필수입니다.")
    private String sex;

    @NotEmpty(message = "이메일 입력은 필수입니다.")
    private String email;

}
