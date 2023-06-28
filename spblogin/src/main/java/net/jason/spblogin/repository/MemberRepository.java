package net.jason.spblogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jason.spblogin.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{
    
}
