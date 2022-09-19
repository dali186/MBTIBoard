package com.example.mbtiboard.config.auth;

//login 주소를에서 로그인 진행
//로그인이 되면 세션 생성 (시큐리티 세션) - Security ContextHolder
//Object -> Authentication 타입 객체 -> Account 정보 -> Account오브젝트 타입 -> UserDeails타입객체

//Security Session -> Authentication -> Account(User)Details(principaldetails)

import com.example.mbtiboard.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private Account account; //컴포지션

    public PrincipalDetails(Account account) {
        this.account = account;
    }

    //해당 account의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return account.getUserRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return account.getUserPasswd();
    }

    @Override
    public String getUsername() {
        return account.getUserEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호 유통기한
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        //1년동안 로그인을 안해서 휴면계정으로 전환 Account에 timpestamp loginDate(마지막 로그인 시간)
        //현재시간 - 로그인 시간 => 1년 초과 retrun false 설정
        //account.getlogindate();

        return true;
    }
}
