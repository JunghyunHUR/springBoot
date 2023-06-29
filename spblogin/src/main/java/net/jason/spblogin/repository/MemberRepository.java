package net.jason.spblogin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jason.spblogin.entity.MemberEntity;


public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{
    //such login eamil information select * from members where mememail = ?
    Optional<MemberEntity> findByMememail(String mememail);
}
