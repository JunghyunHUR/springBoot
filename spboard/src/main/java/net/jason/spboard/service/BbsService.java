package net.jason.spboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<BbsDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 10;
        //select * from spb_bbs order by num desc limit 0, 10
        Page<BbsEntity> bbsEntitis = bbsRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "num")));
        /*
         * bbsEntities.getContent() - 해당 페이지에 있는 글
         * bbsEntities.getTotalElements() - 전체 글의 갯수
         * bbsEntities.getNumber() - 페이지 번호
         * bbsEntities.getTotalPage() - 한 페이지에 보여지는 글의 갯수
         * bbsEntities.getSize() - 한 페이지에 보여지는 글의 갯수
         * bbsEntities.hasPrevious() - 이전 페이지 존재 여부
         * bbsEntities.isFirst() -첫 페이지인지 여부
         * bbsEntities.isLast() - 마지막 페이지 여부
         */
        Page<BbsDto> bbsDtos = bbsEntitis.map(bbs -> new BbsDto(bbs.getNum(), bbs.getBbsWriter(), bbs.getBbsTitle(), bbs.getBbsHits(), bbs.getBbsCreatedTime()));
        return bbsDtos;
    }

    // 인터페이스 Pageable 의 각 메서드 기능
    // 1. unpaged() - pagination 설정이 없는 Pageable 인터페이스 반환
    // 2. ofSize(int pageSize) - pageSize가 지정된 첫 번째 페이지
    // 3. isPaged() - 현재 Pageable에 pagination 정보가 포함되어 있는지 여부
    // 4. isUnpaged()
    // 5. getPageNumber() - 페이지 반환
    // 6. getPageSize()
    // 7. getOffest()
    // 8. getSort()
    // 9. next()
    // 10. previousOfFirst()
    // 11.first()
    // 12.withPage(int pageNumber)
    // 13. hasPrevious()
    // 14. toOptiona()

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

    public BbsDto update(BbsDto bbsDto) {
        BbsEntity bbsEntity = BbsEntity.toUpdateEntity(bbsDto);
        bbsRepository.save(bbsEntity);
        return findById(bbsDto.getNum());
    }

    public void delete(Long num) {
        bbsRepository.deleteById(num);
    }

    // public void addPage(){
    //     BbsDto bbsDto = new BbsDto();
    //     for(int i = 1; i <= 1000 ; i++){
    //         String title = i + "테스트 입니다." + i + "번";
    //         String content = i + "테스트 입니다." + i + "번";
    //         String write = "홍길" + i;
    //         bbsDto.setBbsTitle(title);
    //         bbsDto.setBbsWriter(write);
    //         bbsDto.setBbsContents(content);

    //         BbsEntity bbsEntity = BbsEntity.toSaveEntity(bbsDto);
    //         bbsRepository.save(bbsEntity);
    //     }
    // }

}
