package com.granja.backend.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "porcinos")
public class Porcino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String identificacion;

    @Column(nullable = false)
    private String raza;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private BigDecimal peso;

    @ManyToOne
    @JoinColumn(name = "cliente_cedula")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "porcino_alimentacion",
        joinColumns = @JoinColumn(name = "porcino_id"),
        inverseJoinColumns = @JoinColumn(name = "alimentacion_id")
    )
    private Set<Alimentacion> alimentaciones = new HashSet<>();

    public Porcino() {}

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Set<Alimentacion> getAlimentaciones() { return alimentaciones; }
    public void setAlimentaciones(Set<Alimentacion> alimentaciones) { this.alimentaciones = alimentaciones; }
}
