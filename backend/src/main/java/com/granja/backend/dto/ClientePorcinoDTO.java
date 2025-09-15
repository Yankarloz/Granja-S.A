package com.granja.backend.dto;

import java.math.BigDecimal;

public class ClientePorcinoDTO {
    private String clienteCedula;
    private String clienteNombres;
    private String clienteApellidos;
    private String porcinoIdentificacion;
    private String raza;
    private Integer edad;
    private BigDecimal peso;

    public ClientePorcinoDTO(String clienteCedula, String clienteNombres, String clienteApellidos, String porcinoIdentificacion, String raza, Integer edad, BigDecimal peso) {
        this.clienteCedula = clienteCedula;
        this.clienteNombres = clienteNombres;
        this.clienteApellidos = clienteApellidos;
        this.porcinoIdentificacion = porcinoIdentificacion;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
    }

    // Getters y setters
    public String getClienteCedula() { return clienteCedula; }
    public void setClienteCedula(String clienteCedula) { this.clienteCedula = clienteCedula; }
    public String getClienteNombres() { return clienteNombres; }
    public void setClienteNombres(String clienteNombres) { this.clienteNombres = clienteNombres; }
    public String getClienteApellidos() { return clienteApellidos; }
    public void setClienteApellidos(String clienteApellidos) { this.clienteApellidos = clienteApellidos; }
    public String getPorcinoIdentificacion() { return porcinoIdentificacion; }
    public void setPorcinoIdentificacion(String porcinoIdentificacion) { this.porcinoIdentificacion = porcinoIdentificacion; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }
}
