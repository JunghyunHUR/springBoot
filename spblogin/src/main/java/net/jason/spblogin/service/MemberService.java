package net.jason.spblogin.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.jason.spblogin.dto.MemberDto;
import net.jason.spblogin.entity.MemberEntity;
import net.jason.spblogin.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto){
        // 1. dto ==> change to Entity 
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDto);
        
        // 2. call save method of repository --> send the entity
        memberRepository.save(memberEntity);
    }

    public MemberDto login(MemberDto memberDto) {
        /*
         * such db use email 1. if data exist password is equals with password of data
         */
        Optional<MemberEntity> byMemEmail =  memberRepository.findByMememail(memberDto.getMememail());
        if(byMemEmail.isPresent()){
            //if it's exists
            MemberEntity memberEntity = byMemEmail.get();
            if(memberEntity.getMempass().equals(memberDto.getMempass())){
                //if password equals --> login success
                // conversion to memberDto from entity value
                MemberDto dto = memberDto.toMemberDto(memberEntity);
                return dto;
            }else{
                return null;
            }
        }else{
            //if it's not exists
            return null;
        }
    }
}
