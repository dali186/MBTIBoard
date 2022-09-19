package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.AccountDTO;
import com.example.mbtiboard.service.AccountService;
import lombok.RequiredArgsConstructor;
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
    public String user() {
        return "user";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/admin")
    public String admin() {
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
}
