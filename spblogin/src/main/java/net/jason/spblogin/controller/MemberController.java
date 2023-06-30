package net.jason.spblogin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import net.jason.spblogin.dto.MemberDto;
import net.jason.spblogin.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //생성자 주입
    private final MemberService memberService;

    //회원가입 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDto memberDto) {
        System.out.println("MemberController.save");
        System.out.println("memberDto = " + memberDto);
        memberService.save(memberDto);
        return "index";
    }

    //login
    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("member/login")
    public String login(@ModelAttribute MemberDto memberDto, HttpSession session){
        MemberDto loginResult = memberService.login(memberDto);
        if(loginResult != null){
            // success
            session.setAttribute("loginEmail", loginResult.getMememail());
            return "main";
        }else{
            // fail
            return "login";
        }
    }

    @GetMapping("/member/")
    public String list(Model model){
        List<MemberDto> memberDtoList = memberService.findAll();
        // get from model and send to list
        model.addAttribute("lists", memberDtoList);
        return "list";
    }

    @GetMapping("/member/{num}")
    public String view(@PathVariable int num, Model model){
        MemberDto memberDto = memberService.findById(num);
        model.addAttribute("member", memberDto);
        return "view";
    }
    
}
