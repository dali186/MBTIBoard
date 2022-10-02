package com.example.mbtiboard.controller;

import com.example.mbtiboard.config.auth.PrincipalDetails;
import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.entity.Account;
import com.example.mbtiboard.repository.AccountRepository;
import com.example.mbtiboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;


@Controller
@RequiredArgsConstructor
public class IndexController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

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
        if (result.hasErrors()) {
            return "joinForm";
        }
        accountService.createAccount(accountDTO);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinModify")
    public String updateJoin(Principal principal, Model model) {
        String userId = principal.getName();
        Account account = accountRepository.findByUserEmail(userId);
        model.addAttribute("accountDTO", account);
        return "joinForm";
    }

    @Secured("ROLE_ADMIN")  //EnableGlobalMethodSecurity와 함께 특정 메소드에만 적용시키기
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/adminpage")
    public String adminPage() {
        return "adminPage";
    }

}
