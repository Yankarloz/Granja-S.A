package com.granja.backend.repository;

import com.granja.backend.entity.Porcino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorcinoRepository extends JpaRepository<Porcino, Integer> {
	java.util.List<Porcino> findByCliente_Cedula(String cedula);
	java.util.List<Porcino> findByAlimentaciones_Id(Integer id);
}
