package net.jason.spboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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

    public List<BbsDto> findAll() {
        List<BbsEntity> bbsEntityList = bbsRepository.findAll();
        List<BbsDto> bbsDtoList = new ArrayList<>();    //bbsDtoList 를 빈 배열로 만들고
        for(BbsEntity bbsEntity : bbsEntityList){
            bbsDtoList.add(BbsDto.toSaveDto(bbsEntity));    //bbsDtoList 배열에 toSaveDto한 값을 넣어줌
        }
        return bbsDtoList;
    }

    // 조회수 업데이트
    /*
     * 트랜잭션(더이상 쪼갤수 없는 최소의 연산단위) 
     * - 예를 들어 조회수를 보고 1을 더하려는 순간 다른 상황에 의해 조회수가 늘어나면 계산에 문제가 생김
     * - 다른 예 : 쇼핑몰에서 물건을 보고 주문을 누른 후 결제를 하려 하는데 상품이 바뀌는 경우 문제가 발생
     * - 즉, 상황이 시작되면 db의 업데이트를 막아줌. (연산이 시작되면 다른 연산이 끼어들 수 없도록 한다.)
     */
    @Transactional
    public void updateHits(Long num) {
        bbsRepository.updateHits(num);
    }

    public BbsDto findById(Long num) {
        Optional<BbsEntity> optionalBbsEntity = bbsRepository.findById(num);
        if(optionalBbsEntity.isPresent()){
            BbsEntity bbsEntity = optionalBbsEntity.get();
            BbsDto bbsDto = BbsDto.toSaveDto(bbsEntity);
            return bbsDto;
        }else{
            return null;
        }
    }
}
