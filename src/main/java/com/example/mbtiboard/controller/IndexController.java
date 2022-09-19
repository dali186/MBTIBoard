package com.example.mbtiboard.controller;

import com.example.mbtiboard.config.auth.PrincipalDetails;
import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccountService accountService;
    /*
    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) {
        System.out.println("/test/login =======================");
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        System.out.println("authenticaiton:" + principalDetails.getAccount());

        System.out.println("userDetails:" + userDetails.getUsername());
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String testOAuthLogin(Authentication authentication) {
        System.out.println("/test/ouath/login =======================");
        OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();
        System.out.println("authenticaiton:" + oauth2User.getAttributes());

        return "OAuth 세션 정보 확인하기";
    }
     */

    @GetMapping({"","/"})
    public String index() {
        return "index";
    }

    //ouath,일반 로그인 둘 다 가능
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails:" + principalDetails.getAccount());
        return "user";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/admin")
    public@ResponseBody  String admin() {
        return "admin";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/loginForm";
    }

    @GetMapping("/joinForm")
    public String join(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "joinForm";
    }

    @PostMapping("/joinForm")
    public String acceptJoin(@Validated AccountDTO accountDTO, BindingResult result) {
        if(result.hasErrors()) {
            return"joinForm";
        }
        accountService.createAccount(accountDTO);
        return "redirect:/loginForm";

    }
    @Secured("ROLE_ADMIN")  //EnableGlobalMethodSecurity와 함께 특정 메소드에만 적용시키기
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }


}
