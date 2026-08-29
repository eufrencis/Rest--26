package com.example.demo.repository;

import com.example.demo.entity.LivrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivrosRepository extends JpaRepository <LivrosEntity, Long> {
}
