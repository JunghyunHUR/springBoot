package net.jason.spboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jason.spboard.entity.BbsEntity;

public interface BbsRepository extends JpaRepository<BbsEntity, Long> {
    
}
