package com.care.account;

import com.care.domain.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

//회원가입 폼을 보여주고 회원가입을 처리하는 controller
@Controller
//↓ "final"로 선언된 변수들을 자동으로 생성자에서 주입해주는 롬복 어노테이션(의존성 주입을 쉽게 해줌)
@RequiredArgsConstructor
public class AccountController {

    //회원가입 폼 검사
    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;


    //↓ "" 객체에 대해 검사를 담당할 Validator를 등록하는 함수
    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    //"회원가입 폼을 보여주는 부분"
    @GetMapping("/sign-up")
    public String singUpForm(Model model) {
        //HTMl(jsp)에서 사용할 수 있게 데이터를 넘겨줌↓
        model.addAttribute("signUpForm", new SignUpForm());
        return "account/sign-up";
    }

    //"회원가입을 처리하는 부분" = 사용자가 폼에 작성한 정보를 제출했을 때 처리하는 함수
    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
        //오류가 발생하면 다시 회원가입 폼으로
        if (errors.hasErrors()) {
            return "account/sign-up";
        }

        accountService.processNewAccount(signUpForm);

        //TODO 회원가입 처리
        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model) {
        Account account = accountRepository.findByEmail(email);
        String view = "account/checked-email";

        if (account == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if(!account.getEmailCheckToken().equals(token)){
            model.addAttribute("error", "wrong.token");
            return view;

        }


        account.completeSignUp();
        model.addAttribute("numberOfUser", accountRepository.count());
        model.addAttribute("nickname", account.getNickname());
        return view;
    }

}
