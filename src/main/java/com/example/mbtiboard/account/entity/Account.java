package com.example.mbtiboard.account.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@ToString
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "user_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String provider;
    private String providerId;
    @CreationTimestamp
    private Timestamp create_date;

    @Builder
    public Account(Long userNo, String userEmail, String userPasswd, String userName, String userBirth, String userGender, String userPhone, String userRole, String provider, String providerId) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPasswd = userPasswd;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userRole = userRole;
        this.provider = provider;
        this.providerId = providerId;
    }
}

