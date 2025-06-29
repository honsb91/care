package com.care.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpForm {

    @NotBlank
    @Length(min = 3, max = 20)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$")
    //닉네임
    private String nickname;

    @Email
    @NotBlank
    //이메일
    private String email;

    @NotBlank
    @Length(min = 8, max = 50)
    //비밀번호
    private String password;
}
