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
    private Long user_no;
    private String user_email;
    private String user_passwd;
    private String user_name;
    private String user_birth;
    private String user_gender;
    private String user_phone;
    private String user_role;
    @CreationTimestamp
    private Timestamp createDate;

    @Builder
    public Account(Long user_no, String user_email, String user_passwd, String user_name, String user_birth, String user_gender, String user_phone, String user_role) {
        this.user_no = user_no;
        this.user_email = user_email;
        this.user_passwd = user_passwd;
        this.user_name = user_name;
        this.user_birth = user_birth;
        this.user_gender = user_gender;
        this.user_phone = user_phone;
        this.user_role = user_role;
    }
}

