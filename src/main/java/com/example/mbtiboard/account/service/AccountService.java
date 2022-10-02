package com.example.mbtiboard.account.service;

import com.example.mbtiboard.account.repository.AccountRepository;
import com.example.mbtiboard.account.dto.AccountDTO;
import com.example.mbtiboard.account.entity.Account;
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
        account.setUserRole("ROLE_USER");   //user로 등록
        accountRepository.save(account);
        return account.getUserNo();
    }
}
