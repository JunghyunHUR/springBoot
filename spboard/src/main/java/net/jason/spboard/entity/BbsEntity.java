package net.jason.spboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import net.jason.spboard.dto.BbsDto;

@Entity
@Getter
@Setter
@Table(name="spb_bbs")
public class BbsEntity extends BaseEntity{

    @Id //pk key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long num;

    @Column(length=30, nullable=false) // 크기 지정, not nul
    private String bbsWriter;
    @Column
    private String bbsPass;
    @Column
    private String bbsTitle;
    @Column(length=2000)
    private String bbsContents;
    @Column
    private int bbsHits;

    public static BbsEntity toSaveEntity(BbsDto bbsDto){
        BbsEntity bbsEntity = new BbsEntity();
        bbsEntity.setBbsWriter(bbsDto.getBbsWriter());
        bbsEntity.setBbsPass(bbsDto.getBbsPass());
        bbsEntity.setBbsTitle(bbsDto.getBbsTitle());
        bbsEntity.setBbsContents(bbsDto.getBbsContents());
        bbsEntity.setBbsHits(0);
        return bbsEntity;
    }
}