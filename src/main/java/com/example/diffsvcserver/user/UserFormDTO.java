package com.example.diffsvcserver.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserFormDTO {
    @ApiModelProperty(example = "exampleUser")
    @NotBlank(message = "아이디 입력은 필수입니다.")
    private String userId;

    @ApiModelProperty(example = "Passw0rd!")
    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String userPwd;

    @ApiModelProperty(example = "남자")
    @NotBlank(message = "성별 입력은 필수입니다.")
    private String sex;

    @ApiModelProperty(example = "user@example.com")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
