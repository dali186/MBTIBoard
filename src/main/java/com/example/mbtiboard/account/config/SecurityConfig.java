package com.example.mbtiboard.account.config;

import com.example.mbtiboard.account.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity  //활성화 (스프링 시큐리티 필터가 스프링 필터체인에 등록)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)  // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    //비밀번호 암호화
    @Bean   //리턴되는 오브젝트를 IoC로 등록
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()//로그인 페이지로 이동
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")  // /login 주소 호출이 되면서 시큐리티가 낚아채서 대신 로그인을 진행
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")//외부 로그인을 로그인 폼에 추가 ?code(인증) -> accessToken(권한) -> 사용자 프로필정보 가져옴 -> 그 정보를 토대로 회원가입 진행
                .userInfoEndpoint()
                .userService(principalOauth2UserService);// 로그인 완료된 후 후처리 -> 엑세스 토큰 + 사용자 프로필정보
    }
}

