package com.example.demo.taco.repository;

import com.example.demo.taco.model.TacoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends JpaRepository<TacoEntity, Long> {

}