package com.example.mbtiboard.account.config.oauth;

import com.example.mbtiboard.account.config.auth.PrincipalDetails;
import com.example.mbtiboard.account.config.oauth.provider.FacebookUserInfo;
import com.example.mbtiboard.account.config.oauth.provider.GoogleUserInfo;
import com.example.mbtiboard.account.config.oauth.provider.OAuth2UserInfo;
import com.example.mbtiboard.account.entity.Account;
import com.example.mbtiboard.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    //구글 로그인 후처리

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration :" + userRequest.getClientRegistration());  //registrationId로 어떤 OAuth로 로그인 했는지
        System.out.println("getAccessToken :" + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //구글 로그인 -> 구글 로그인 창 -> 로그인 -> code 리턴 (OAuth-client) -> AccessToken
        //userRequset -> loadUser 함수 호출 -> 회원정보 받음
        System.out.println("getAttributes :" + oAuth2User.getAttributes());

        //회원가입
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        } else {

        }
        String provider = oAuth2UserInfo.getProvider();    //플랫폼
        String providerId = oAuth2UserInfo.getProviderId(); //providerId
        String userEmail = oAuth2UserInfo.getEmail();
        String userName = oAuth2UserInfo.getName();
        String userPasswd = new BCryptPasswordEncoder().encode("password");
        String userRole = "ROLE_USER";
        String userBirth = "";
        String userGender = "";
        String userPhone = "";

        //중복체크
        Account accountEntity = accountRepository.findByUserEmail(userEmail);

        if(accountEntity == null) {
            accountEntity = Account.builder()
                    .userEmail(userEmail)
                    .userPasswd(userPasswd)
                    .userName(userName)
                    .userBirth(userBirth)
                    .userGender(userGender)
                    .userPhone(userPhone)
                    .userRole(userRole)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            accountRepository.save(accountEntity);
            System.out.println("회원가입 완료");
        } else {
            System.out.println("이미 회원가입 되어있습니다.");
        }

        return new PrincipalDetails(accountEntity, oAuth2User.getAttributes());
    }
    //username = google_(sub) , password = "암호화(password)" email = email, role은 강제 삽임
}
