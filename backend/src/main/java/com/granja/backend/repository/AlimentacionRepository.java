package com.granja.backend.repository;

import com.granja.backend.entity.Alimentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentacionRepository extends JpaRepository<Alimentacion, Integer> {
}
