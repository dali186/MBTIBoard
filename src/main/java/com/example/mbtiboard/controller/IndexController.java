package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping({"","/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
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

    private final AccountService accountService;

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
