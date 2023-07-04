package net.jason.spboard.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.jason.spboard.dto.BbsDto;
import net.jason.spboard.entity.BbsEntity;
import net.jason.spboard.repository.BbsRepository;

@Service
@RequiredArgsConstructor
public class BbsService {
    private final BbsRepository bbsRepository;
    
    public void save(BbsDto bbsDto){
        BbsEntity bbsEntity = BbsEntity.toSaveEntity(bbsDto);
        bbsRepository.save(bbsEntity);
    }
}
