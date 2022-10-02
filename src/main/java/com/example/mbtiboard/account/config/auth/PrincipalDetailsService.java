package com.example.mbtiboard.account.config.auth;

import com.example.mbtiboard.account.entity.Account;
import com.example.mbtiboard.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcessingUrl("/login");
// /login요청이 오면 자동으로 UserDetailsService 타입으로 Ioc되어있는 loadUserByUsername 함수 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    //form의 name과 매칭 or usernameParameter(config)수정
    //Security Session ( Authentication(UserDetails) ) )
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account accountEntity = accountRepository.findByUserEmail(username);
        if(accountEntity != null) {
            return new PrincipalDetails(accountEntity);
        }
        return null;
    }
}

