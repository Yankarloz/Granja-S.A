package com.granja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granja.backend.entity.Prueba;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Long> {
}
