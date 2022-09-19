package com.example.mbtiboard.repository;


import com.example.mbtiboard.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD
public interface AccountRepository extends JpaRepository<Account, Long> {

    //규칙 findBy테이블이름    (qeury Method)
    //select * from account where 이메일
    public Account findByUserEmail(String username);
}

