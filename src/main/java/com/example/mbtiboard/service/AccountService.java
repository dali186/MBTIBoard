package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.entity.Account;
import com.example.mbtiboard.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createAccount(AccountDTO accountDTO) {
        Account account = accountDTO.toEntity();
        account.setUser_role("USER");   //user로 등록
        accountRepository.save(account);

        return account.getUser_no();
    }
}
