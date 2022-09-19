package com.example.mbtiboard.dto;

import com.example.mbtiboard.entity.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Long user_no;
    private String user_email;
    private String user_passwd;
    private String user_name;
    private String user_birth;
    private String user_gender;
    private String user_phone;
    private String user_role;

    @Builder
    public AccountDTO(Long user_no, String user_email, String user_passwd, String user_name, String user_birth, String user_gender, String user_phone, String user_role) {
        this.user_no = user_no;
        this.user_email = user_email;
        this.user_passwd = user_passwd;
        this.user_name = user_name;
        this.user_birth = user_birth;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_role = user_role;
    }

    public Account toEntity() {
        return Account.builder()
                .user_no(user_no)
                .user_email(user_email)
                .user_passwd(new BCryptPasswordEncoder().encode(user_passwd))
                .user_name(user_name)
                .user_birth(user_birth)
                .user_gender(user_gender)
                .user_phone(user_phone)
                .user_role(user_role)
                .build();
    }
}
