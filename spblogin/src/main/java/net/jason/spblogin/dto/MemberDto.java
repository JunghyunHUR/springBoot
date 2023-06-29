package net.jason.spblogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.jason.spblogin.entity.MemberEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {
    private int num;
    private String mememail;
    private String mempass;
    private String memname;

    public MemberDto toMemberDto(MemberEntity memberEntity){
        MemberDto memberDto = new MemberDto();
        memberDto.setNum(memberEntity.getNum());
        memberDto.setMememail(memberEntity.getMememail());
        memberDto.setMempass(memberEntity.getMempass());
        memberDto.setMemname(memberEntity.getMemname());
        return memberDto;
    }
}
