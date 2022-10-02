package com.example.mbtiboard.account.dto;

import com.example.mbtiboard.account.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Long userNo;

    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_passwd")
    private String userPasswd;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_birth")
    private String userBirth;
    @Column(name = "user_gender")
    private String userGender;
    @Column(name = "user_phone")
    private String userPhone;
    @Column(name = "user_role")
    private String userRole;

    @Builder
    public AccountDTO(Long userNo, String userEmail, String userPasswd, String userName, String userBirth, String userGender, String userPhone, String userRole) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPasswd = userPasswd;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userRole = userRole;
    }

    public Account toEntity() {
        return Account.builder()
                .userNo(userNo)
                .userEmail(userEmail)
                .userPasswd(new BCryptPasswordEncoder().encode(userPasswd))
                .userName(userName)
                .userBirth(userBirth)
                .userGender(userGender)
                .userPhone(userPhone)
                .userRole(userRole)
                .build();
    }
}
