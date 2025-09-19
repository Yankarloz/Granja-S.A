package com.granja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granja.backend.entity.Alimentacion;

@Repository
public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer> {
}
