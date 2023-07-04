package net.jason.spboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import net.jason.spboard.dto.BbsDto;
import net.jason.spboard.service.BbsService;

@Controller
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class SpBoardController {

    private final BbsService bbsService;
    
    @GetMapping("/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BbsDto bbsDto){
        System.out.println("bbsDto = " + bbsDto);
        return "index";
    }
}
