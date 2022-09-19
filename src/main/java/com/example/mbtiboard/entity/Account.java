package com.example.mbtiboard.entity;

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
    private String userEmail;
    private String userPasswd;
    private String userName;
    private String userBirth;
    private String userGender;
    private String userPhone;
    private String userRole;
    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Account(Long userNo, String userEmail, String userPasswd, String userName, String userBirth, String userGender, String userPhone, String userRole) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPasswd = userPasswd;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userRole = userRole;
    }
}

