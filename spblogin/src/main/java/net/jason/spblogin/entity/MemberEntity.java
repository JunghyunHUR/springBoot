package net.jason.spblogin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import net.jason.spblogin.dto.MemberDto;

@Entity
@Setter
@Getter
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가(auto_increment)
    private int num;

    @Column(unique = true)
    private String mememail;
    
    @Column
    private String mempass;

    @Column
    private String memname;

    public static MemberEntity toMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMememail(memberDto.getMememail());
        memberEntity.setMemname(memberDto.getMemname());
        memberEntity.setMempass(memberDto.getMempass());
        return memberEntity;
    }

    
}
