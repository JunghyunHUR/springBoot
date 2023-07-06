package net.jason.spboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        bbsService.save(bbsDto);
        return "index";
    }

    @GetMapping("/")
    public String findAll(Model model){
        //게시물 목록 보기
        List<BbsDto> bbsDtoList = bbsService.findAll();
        model.addAttribute("list", bbsDtoList);
        return "list";
    }
    
    @GetMapping("/{num}")
    public String findById(@PathVariable Long num, Model model){
        bbsService.updateHits(num);
        BbsDto bbsDto = bbsService.findById(num);
        model.addAttribute("bbs", bbsDto);
        return "detail";
    }

    @GetMapping("/update/{num}")
    public String updateForm(@PathVariable Long num, Model model){
        BbsDto bbsDto = bbsService.findById(num);
        model.addAttribute("bbs", bbsDto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BbsDto bbsDto, Model model){
        BbsDto bbs = bbsService.update(bbsDto);
        model.addAttribute("bbs",bbs);
        // return "detail";
        return "redirect:/bbs/" + bbsDto.getNum();
    }

    @GetMapping("delete/{num}")
    public String delete(@PathVariable Long num){
        bbsService.delete(num);
        return "redirect:/bbs/";
    }
}
