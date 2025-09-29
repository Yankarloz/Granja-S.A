package com.granja.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.granja.backend.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
