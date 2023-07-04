package net.jason.spboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor //BbsDto(){}
public class BbsDto {
    private Long num;
    private String bbsWriter;
    private String bbsPass;
    private String bbsTitle;
    private String bbsContents;
    private int bbsHits;
    private LocalDateTime bbsCreatedTime;
    private LocalDateTime bbsUpdateTime;
    
}
