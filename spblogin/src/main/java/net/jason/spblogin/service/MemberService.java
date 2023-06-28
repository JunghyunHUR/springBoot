package net.jason.spblogin.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.jason.spblogin.dto.MemberDto;
import net.jason.spblogin.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        
    }
}
