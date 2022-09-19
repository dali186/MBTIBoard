package com.example.mbtiboard.dto;

import com.example.mbtiboard.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Long userNo;
    private String userEmail;
    private String userPasswd;
    private String userName;
    private String userBirth;
    private String userGender;
    private String userPhone;
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
