package net.jason.spblogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //기본 요청 매소드 작성
    @GetMapping("/")
    public String index() {
        return "index"; // ==> templates 폴더에 index.html 으로 찾아간다.
    }
}
